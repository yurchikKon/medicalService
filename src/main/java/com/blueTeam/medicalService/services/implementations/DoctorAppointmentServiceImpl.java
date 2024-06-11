package com.blueTeam.medicalService.services.implementations;

import com.blueTeam.medicalService.dto.user.doctor.appointment.AppointmentTimeDto;
import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentCreateEditDto;
import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;
import com.blueTeam.medicalService.entities.Doctor;
import com.blueTeam.medicalService.entities.DoctorTimetable;
import com.blueTeam.medicalService.entities.DoctorAppointment;
import com.blueTeam.medicalService.entities.Patient;
import com.blueTeam.medicalService.exceptions.ResourceAlreadyExistException;
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
import java.util.ArrayList;
import java.util.List;

import static com.blueTeam.medicalService.entities.enums.Status.*;
import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorAppointmentServiceImpl implements DoctorAppointmentService {
    private final DoctorAppointmentRepository doctorAppointmentRepository;
    private final DoctorRepository doctorRepository;
    private final DoctorTimetableRepository doctorTimetableRepository;
    private final PatientRepository patientRepository;

    @Override
    public List<DoctorAppointmentRepresentationDto> findAllScheduledByDoctorIdAndDate(Long id, LocalDate localDate) {
        /*if (doctorRepository.existsById(id)) {
            log.info("All appointments on {} of doctor with id = {} returned", localDate, id);
            return doctorAppointmentRepository.findAllScheduledByDoctorIdAndDate(id, localDate)
                .stream()
                .map()
                .toList();
        } else {
            throw new EntityNotFoundException("Doctor with such id does not exist");
        }*/
        return null;
    }

    @Override
    public List<AppointmentTimeDto> findAllFreeAppointmentsByDoctorIdAndDate(Long id, LocalDate localDate) {
        /*if (doctorRepository.existsById(id)) {
            DoctorTimetable doctorTimetable = doctorTimetableRepository.findByDoctorIdAndDayOfWeek(id, localDate.getDayOfWeek());

            List<AppointmentTimeDto> appointmentTimeDtoList = findAllScheduledByDoctorIdAndDate(id, localDate)
                .stream()
                .map()
                .toList();
            List<AppointmentTimeDto> freeAppointments = generateAllAppointmentTimeForTimetable(doctorTimetable);
            freeAppointments.removeAll(appointmentTimeDtoList);
            log.info("All free appointments on {} of doctor with id = {} returned", localDate, id);

            return freeAppointments;
        } else {
            throw new EntityNotFoundException("Doctor with such id does not exist");
        }*/return null;
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public DoctorAppointmentCreateEditDto createAppointment(DoctorAppointmentCreateEditDto dto) {
        /*Doctor doctor = doctorRepository.findById(dto.doctorDto().getId())
            .orElseThrow(() -> new EntityNotFoundException("Doctor with such id does not exist"));
        Patient patient = patientRepository.findById(dto.patientDto().getId())
            .orElseThrow(() -> new EntityNotFoundException("Patient with such id does not exist"));
        List<DoctorAppointment> doctorAppointmentList = doctorAppointmentRepository
            .findAllScheduledByDoctorIdAndDate(doctor.getId(), dto.dateTime().toLocalDate());

        if (doctorAppointmentList.stream().noneMatch(app -> app.getDateTime().toLocalTime().equals(dto.dateTime().toLocalTime()))) {
            DoctorAppointment doctorAppointment = DoctorAppointment.builder()
                .doctor(doctor)
                .patient(patient)
                .dateTime(LocalDateTime.of(dto.dateTime().toLocalDate(), dto.dateTime().toLocalTime()))
                .status(SCHEDULED)
                .build();
            doctorAppointmentRepository.save(doctorAppointment);
            log.info("New appointment for patient with id {} to doctor with id {} on {} {} was created",
                patient.getId(), doctor.getId(), dto.dateTime().toLocalDate(), dto.dateTime().toLocalTime());

            return ;
        } else {
            throw new ResourceAlreadyExistException("Sorry, appointment for this time has already been created");
        }*/ return null;
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public DoctorAppointmentCreateEditDto cancelAppointment(Long appointmentId) {
        /*DoctorAppointment doctorAppointment = doctorAppointmentRepository
            .findById(appointmentId)
            .orElseThrow(() -> new EntityNotFoundException("No appointments was found with such id"));

        doctorAppointment.setStatus(CANCELED);
        doctorAppointmentRepository.save(doctorAppointment);
        log.info("Scheduled appointment with id {} was canceled", appointmentId);

        return ;*/ return null;
    }

    private List<AppointmentTimeDto> generateAllAppointmentTimeForTimetable(DoctorTimetable doctorTimetable) {
        List<AppointmentTimeDto> appointmentTimeDtoList = new ArrayList<>();
        LocalTime time = doctorTimetable.getTimeStart();
        long numberOfAppointments = Duration.between(doctorTimetable.getTimeStart(), doctorTimetable.getTimeEnd()).toMinutes() / 30;

        for (int i = (int) numberOfAppointments; i > 0; i--) {
            appointmentTimeDtoList.add(new AppointmentTimeDto(time));
            time = time.plusMinutes(30);
        }

        return appointmentTimeDtoList;
    }
}
