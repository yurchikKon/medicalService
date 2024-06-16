package com.blueTeam.medicalService.services.implementations;

import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentCreateEditDto;
import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;
import com.blueTeam.medicalService.entities.Doctor;
import com.blueTeam.medicalService.entities.DoctorAppointment;
import com.blueTeam.medicalService.entities.DoctorTimetable;
import com.blueTeam.medicalService.entities.Patient;
import com.blueTeam.medicalService.entities.enums.Notification;
import com.blueTeam.medicalService.exceptions.InvalidStateException;
import com.blueTeam.medicalService.exceptions.ResourceAlreadyExistException;
import com.blueTeam.medicalService.mapper.DoctorAppointmentMapper;
import com.blueTeam.medicalService.mapper.DoctorMapper;
import com.blueTeam.medicalService.repositories.DoctorAppointmentRepository;
import com.blueTeam.medicalService.repositories.DoctorRepository;
import com.blueTeam.medicalService.repositories.DoctorTimetableRepository;
import com.blueTeam.medicalService.repositories.PatientRepository;
import com.blueTeam.medicalService.services.interfaces.DoctorAppointmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static com.blueTeam.medicalService.entities.enums.Notification.PLANED;
import static com.blueTeam.medicalService.entities.enums.Status.CANCELED;
import static com.blueTeam.medicalService.entities.enums.Status.SCHEDULED;
import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorAppointmentServiceImpl implements DoctorAppointmentService {
    private final DoctorAppointmentRepository doctorAppointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final DoctorAppointmentMapper doctorAppointmentMapper;
    private final DoctorMapper doctorMapper;

    @Transactional(readOnly = true)
    @Override
    public List<DoctorAppointmentRepresentationDto> findAllScheduledByDoctorIdAndDate(Long id, LocalDate localDate) {
        if (doctorRepository.existsById(id)) {
            log.info("All appointments on {} of doctor with id = {} returned", localDate, id);
            return doctorAppointmentRepository.findAllScheduledByDoctorIdAndDate(id, localDate)
                .stream()
                .map(doctorAppointmentMapper::mapToDto)
                .toList();
        } else {
            throw new EntityNotFoundException("Doctor with such id does not exist");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<DoctorAppointmentRepresentationDto> findAllFreeAppointmentsByDoctorIdAndDate(Long id, LocalDate localDate) {
        Doctor doctor = doctorRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Doctor with such id does not exist"));
        Set<LocalDateTime> dateTimeSet = fillDateTimeSet(findAllScheduledByDoctorIdAndDate(id, localDate));

        List<DoctorAppointmentRepresentationDto> freeAppointments = generateAppointments(doctor, localDate)
            .stream()
            .filter(dto -> !dateTimeSet.contains(dto.getDateTime()))
            .toList();
        log.info("All free appointments on {} of doctor with id = {} returned", localDate, id);

        return freeAppointments;
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public DoctorAppointmentRepresentationDto createAppointment(Long patientId, Long doctorId, LocalDateTime dateTime) {
        Doctor doctor = doctorRepository.findById(doctorId)
            .orElseThrow(() -> new EntityNotFoundException("Doctor with such id does not exist"));
        Patient patient = patientRepository.findById(patientId)
            .orElseThrow(() -> new EntityNotFoundException("Patient with such id does not exist"));
        List<DoctorAppointment> doctorAppointmentList = doctorAppointmentRepository
            .findAllScheduledByDoctorIdAndDate(doctor.getId(), dateTime.toLocalDate());

        if (doctorAppointmentList.stream().noneMatch(app -> app.getDateTime().toLocalTime().equals(dateTime.toLocalTime()))) {
            checkAppointmentDateTime(dateTime);
            DoctorAppointment doctorAppointment = DoctorAppointment.builder()
                .doctor(doctor)
                .patient(patient)
                .dateTime(LocalDateTime.of(dateTime.toLocalDate(), dateTime.toLocalTime()))
                .status(SCHEDULED)
                .notification(PLANED)
                .build();
            doctorAppointmentRepository.save(doctorAppointment);
            log.info("New appointment for patient with id {} to doctor with id {} on {} {} was created",
                patient.getId(), doctor.getId(), dateTime.toLocalDate(), dateTime.toLocalTime());

            return doctorAppointmentMapper.mapToDto(doctorAppointment);
        } else {
            throw new ResourceAlreadyExistException("Sorry, appointment for this time has already been created");
        }

    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public DoctorAppointmentRepresentationDto cancelAppointment(Long appointmentId) {
        DoctorAppointment doctorAppointment = doctorAppointmentRepository
            .findById(appointmentId)
            .orElseThrow(() -> new EntityNotFoundException("No appointments was found with such id"));

        if (doctorAppointment.getStatus().equals(SCHEDULED)) {
            doctorAppointment.setStatus(CANCELED);
            doctorAppointmentRepository.save(doctorAppointment);
            log.info("Scheduled appointment with id {} was canceled", appointmentId);

            return doctorAppointmentMapper.mapToDto(doctorAppointment);
        }
        else {
            throw new InvalidStateException("Appointment with such id is not scheduled");
        }
    }

    private void checkAppointmentDateTime(LocalDateTime dateTime) {
        int minutes = dateTime.toLocalTime().getMinute();
        log.info(String.valueOf(Duration.between(LocalDateTime.now(), dateTime).getSeconds()));

        if (dateTime.isBefore(LocalDateTime.now())){
            throw new InvalidStateException("You can not create appointment for past time");
        }
        else if (Duration.between(LocalDateTime.now(), dateTime).getSeconds() > 1814400){
            throw new InvalidStateException("You can not create appointment for date greater then current for 3 weeks");
        }
        else if(!(minutes == 0 || minutes == 30)) {
            throw new InvalidStateException("You can not create appointment for this time");
        }
    }

    private List<DoctorAppointmentRepresentationDto> generateAppointments(Doctor doctor, LocalDate date) {
        List<DoctorAppointmentRepresentationDto> dtoList = new ArrayList<>();
        DoctorTimetable doctorTimetable = doctor.getTimetable()
            .stream()
            .filter(timetable -> timetable.getDayOfWeek().equals(date.getDayOfWeek()))
            .findFirst()
            .orElseThrow(() -> new EntityNotFoundException("This doctor does not have timetable for this day"));

        LocalTime time;
        if (!date.equals(LocalDate.now())){
            time = doctorTimetable.getTimeStart();
        }
        else {
            time = roundTime(LocalTime.now());
        }
        long numberOfAppointments = Duration.between(time, doctorTimetable.getTimeEnd()).toMinutes() / 30;

        for (int i = (int) numberOfAppointments; i > 0; i--) {
            DoctorAppointmentRepresentationDto dto = DoctorAppointmentRepresentationDto.builder()
                .dateTime(LocalDateTime.of(date, time))
                .doctorDto(doctorMapper.mapToDto(doctor))
                .build();
            dtoList.add(dto);
            time = time.plusMinutes(30);
        }
        return dtoList;
    }

    private LocalTime roundTime(LocalTime currentTime) {
        if(currentTime.getMinute() < 30){
            return currentTime.withMinute(30).withSecond(0);
        }
        else {
            return currentTime.plusHours(1).withMinute(0).withSecond(0);
        }
    }

    private Set<LocalDateTime> fillDateTimeSet(List<DoctorAppointmentRepresentationDto> dtoList) {
        Set<LocalDateTime> dateTimeSet = new HashSet<>();

        for (DoctorAppointmentRepresentationDto dto : dtoList) {
            dateTimeSet.add(dto.getDateTime());
        }
        return dateTimeSet;
    }
}
