package com.blueTeam.medicalService.services.implementations;

import com.blueTeam.medicalService.dto.doctorsAppointment.AppointmentTimeDto;
import com.blueTeam.medicalService.entities.DoctorTimetable;
import com.blueTeam.medicalService.entities.DoctorAppointment;
import com.blueTeam.medicalService.entities.enums.Status;
import com.blueTeam.medicalService.repositories.DoctorAppointmentRepository;
import com.blueTeam.medicalService.repositories.DoctorRepository;
import com.blueTeam.medicalService.repositories.DoctorTimetableRepository;
import com.blueTeam.medicalService.repositories.PatientRepository;
import com.blueTeam.medicalService.services.interfaces.DoctorAppointmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorAppointmentServiceImpl implements DoctorAppointmentService {
    private final DoctorAppointmentRepository doctorAppointmentRepository;
    private final DoctorRepository doctorRepository;
    private final DoctorTimetableRepository doctorTimetableRepository;
    private final PatientRepository patientRepository;

    @Override
    public List<DoctorAppointment> findAllByDoctorIdAndDate(Long id, LocalDate localDate) {
        if (checkDoctorExists(id)) {
            log.info("All appointments on {} of doctor with id = {} returned", localDate, id);
            return doctorAppointmentRepository.findAllByDoctorIdAndDate(id, localDate);
        } else {
            throw new EntityNotFoundException("Doctor with such id does not exist");
        }
    }

    @Override
    public List<AppointmentTimeDto> findAllFreeAppointmentsByDoctorIdAndDate(Long id, LocalDate localDate) {
        if (checkDoctorExists(id)) {
            DoctorTimetable doctorTimetable = doctorTimetableRepository.findByDoctorIdAndDayOfWeek(id, localDate.getDayOfWeek());
            List<AppointmentTimeDto> appointmentTimeDtoList = findAllByDoctorIdAndDate(id, localDate);
            List<AppointmentTimeDto> freeAppointments = generateAllAppointmentTimeForTimetable(doctorTimetable);
            freeAppointments.removeAll(appointmentTimeDtoList);
            log.info("All free appointments on {} of doctor with id = {} returned", localDate, id);
            return freeAppointments;
        }
        else {
            throw new EntityNotFoundException("Doctor with such id does not exist");
        }
    }

    @Override
    public DoctorAppointment createAppointment(Long doctorId, Long patientId, LocalDate date, LocalTime time) {
        List<DoctorAppointment> doctorAppointmentList = doctorAppointmentRepository.findAllByDoctorIdAndDate(doctorId, date);

        if(doctorAppointmentList.stream().noneMatch(app -> app.getDateTime().toLocalTime().equals(time))) {
            DoctorAppointment doctorAppointment = DoctorAppointment.builder()
                .doctor(doctorRepository.findById(doctorId)
                    .orElseThrow(() -> new EntityNotFoundException("Doctor with such id does not exist")))
                .patient(patientRepository.findById(patientId)
                    .orElseThrow(() -> new EntityNotFoundException("Patient with such id does not exist")))
                .dateTime(LocalDateTime.of(date,time))
                .status(Status.SCHEDULE)
                .build();
            doctorAppointmentRepository.save(doctorAppointment)
        }

    }

    private boolean checkDoctorExists(Long id) {
        return doctorRepository.findById(id).isPresent();
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
