package com.blueTeam.medicalService.services.implementations;

import com.blueTeam.medicalService.dto.doctorsAppointment.AppointmentTimeDto;
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
    public List<DoctorAppointment> findAllScheduledByDoctorIdAndDate(Long id, LocalDate localDate) {
        if (doctorRepository.existsById(id)) {
            log.info("All appointments on {} of doctor with id = {} returned", localDate, id);
            return doctorAppointmentRepository.findAllScheduledByDoctorIdAndDate(id, localDate);
        } else {
            throw new EntityNotFoundException("Doctor with such id does not exist");
        }
    }

    @Override
    public List<AppointmentTimeDto> findAllFreeAppointmentsByDoctorIdAndDate(Long id, LocalDate localDate) {
        if (doctorRepository.existsById(id)) {
            DoctorTimetable doctorTimetable = doctorTimetableRepository.findByDoctorIdAndDayOfWeek(id, localDate.getDayOfWeek());
            List<AppointmentTimeDto> appointmentTimeDtoList = findAllScheduledByDoctorIdAndDate(id, localDate);
            List<AppointmentTimeDto> freeAppointments = generateAllAppointmentTimeForTimetable(doctorTimetable);
            freeAppointments.removeAll(appointmentTimeDtoList);
            log.info("All free appointments on {} of doctor with id = {} returned", localDate, id);
            return freeAppointments;
        } else {
            throw new EntityNotFoundException("Doctor with such id does not exist");
        }
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public DoctorAppointment createAppointment(Long doctorId, Long patientId, LocalDate date, LocalTime time) {
        Doctor doctor = doctorRepository.findById(doctorId)
            .orElseThrow(() -> new EntityNotFoundException("Doctor with such id does not exist"));
        Patient patient = patientRepository.findById(patientId)
            .orElseThrow(() -> new EntityNotFoundException("Patient with such id does not exist"));
        List<DoctorAppointment> doctorAppointmentList = doctorAppointmentRepository.findAllScheduledByDoctorIdAndDate(doctorId, date);

        if (doctorAppointmentList.stream().noneMatch(app -> app.getDateTime().toLocalTime().equals(time))) {
            DoctorAppointment doctorAppointment = DoctorAppointment.builder()
                .doctor(doctor)
                .patient(patient)
                .dateTime(LocalDateTime.of(date, time))
                .status(SCHEDULE)
                .build();

            return doctorAppointmentRepository.save(doctorAppointment);
        } else {
            throw new ResourceAlreadyExistException("Sorry, appointment for this time has already been created");
        }
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public DoctorAppointment cancelAppointment(Long doctorId, Long patientId, LocalDate date, LocalTime time) {
        DoctorAppointment doctorAppointment = doctorAppointmentRepository
            .findByDoctorIdAndPatientIdAndDateTime(doctorId, patientId, LocalDateTime.of(date, time))
            .orElseThrow(() -> new EntityNotFoundException("No appointments was found with such details"));
        doctorAppointment.setStatus(CANCELED);

        return doctorAppointmentRepository.save(doctorAppointment);
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
