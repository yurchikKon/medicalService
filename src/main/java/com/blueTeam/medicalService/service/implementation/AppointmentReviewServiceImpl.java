package com.blueTeam.medicalService.service.implementation;

import com.blueTeam.medicalService.dto.user.doctor.appointment.AppointmentReviewDto;
import com.blueTeam.medicalService.entity.AppointmentReview;
import com.blueTeam.medicalService.entity.Doctor;
import com.blueTeam.medicalService.entity.DoctorAppointment;
import com.blueTeam.medicalService.entity.enums.Status;
import com.blueTeam.medicalService.exception.InvalidStateException;
import com.blueTeam.medicalService.mapper.AppointmentReviewMapper;
import com.blueTeam.medicalService.repository.AppointmentReviewRepository;
import com.blueTeam.medicalService.repository.DoctorAppointmentRepository;
import com.blueTeam.medicalService.repository.DoctorRepository;
import com.blueTeam.medicalService.service.AppointmentReviewService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentReviewServiceImpl implements AppointmentReviewService {
    private final DoctorAppointmentRepository doctorAppointmentRepository;
    private final AppointmentReviewRepository appointmentReviewRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentReviewMapper appointmentReviewMapper;

    @Override
    @Transactional
    public AppointmentReviewDto estimateAppointment(Long appointmentId, Double mark) {
        DoctorAppointment doctorAppointment = doctorAppointmentRepository.findById(appointmentId)
            .orElseThrow(() -> new EntityNotFoundException("No appointment with such id"));
        if (doctorAppointment.getStatus().equals(Status.COMPLETED) && doctorAppointment.getAppointmentReview() == null) {
            AppointmentReview appointmentReview = AppointmentReview.builder()
                .doctorsAppointment(doctorAppointment)
                .mark(mark)
                .build();

            appointmentReviewRepository.save(appointmentReview);
            log.info("Appointment review for appointment with id {} was created", appointmentId);
            return appointmentReviewMapper.mapToDto(appointmentReview);
        }
        else {
            throw new InvalidStateException("You can not estimate this appointment");
        }
    }

    @Override
    @Transactional
    @Scheduled(cron = "${app.scheduled.cron}")
    public void doctorsRateProcess() {
        List<Doctor> doctorList = doctorRepository.findAll();

        for (Doctor doctor : doctorList) {
            Double mark = doctor.getDoctorsAppointments()
                .stream()
                .filter(app -> app.getAppointmentReview() != null)
                .mapToDouble(app -> app.getAppointmentReview().getMark())
                .average()
                .orElseThrow(() -> new EntityNotFoundException("Appointment does not have marks"));
            doctor.setRate(mark);
            doctorRepository.save(doctor);
            log.info("Doctor with id {} received average mark {}", doctor.getId(), mark);
        }
    }
}
