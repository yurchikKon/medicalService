package com.blueTeam.medicalService.services.implementations;

import com.blueTeam.medicalService.dto.user.doctor.appointment.AppointmentReviewDto;
import com.blueTeam.medicalService.entities.AppointmentReview;
import com.blueTeam.medicalService.entities.Doctor;
import com.blueTeam.medicalService.entities.DoctorAppointment;
import com.blueTeam.medicalService.entities.enums.Status;
import com.blueTeam.medicalService.exceptions.InvalidStateException;
import com.blueTeam.medicalService.mapper.AppointmentReviewMapper;
import com.blueTeam.medicalService.repositories.AppointmentReviewRepository;
import com.blueTeam.medicalService.repositories.DoctorAppointmentRepository;
import com.blueTeam.medicalService.repositories.DoctorRepository;
import com.blueTeam.medicalService.services.interfaces.AppointmentReviewService;
import com.blueTeam.medicalService.services.interfaces.DoctorAppointmentService;
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
            return appointmentReviewMapper.mapToDto(appointmentReview);
        }
        else {
            throw new InvalidStateException("You can not estimate this appointment");
        }
    }

    @Override
    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
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
        }
    }
}
