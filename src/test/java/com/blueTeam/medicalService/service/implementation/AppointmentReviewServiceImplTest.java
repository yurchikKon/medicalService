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
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.blueTeam.medicalService.entity.enums.Status.COMPLETED;
import static com.blueTeam.medicalService.entity.enums.Status.SCHEDULED;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppointmentReviewServiceImplTest {
    private static final AppointmentReviewDto APPOINTMENT_REVIEW_DTO = new AppointmentReviewDto(1L, 5.00);
    private static final Long APPOINTMENT_ID = 1L;
    private static final Double APPOINTMENT_MARK = 5.00;
    private static final DoctorAppointment APPOINTMENT = createAppointment(1L, COMPLETED,
            createReview(5.00, null));

    private static final DoctorAppointment APPOINTMENT_SCHEDULED = createAppointment(1L, SCHEDULED, null);
    private static final DoctorAppointment NOT_RATED_APPOINTMENT = createAppointment(1L, COMPLETED, null);

    private static final AppointmentReview APPOINTMENT_REVIEW = createReview(5.00, NOT_RATED_APPOINTMENT);
    private static final Doctor DOCTOR = createDoctor(1L, APPOINTMENT);
    private static final Doctor DOCTOR_NOT_RATED = createDoctor(1L, NOT_RATED_APPOINTMENT);


    @InjectMocks
    AppointmentReviewServiceImpl appointmentReviewService;

    @Mock
    DoctorAppointmentRepository doctorAppointmentRepository;

    @Mock
    AppointmentReviewRepository appointmentReviewRepository;

    @Mock
    AppointmentReviewMapper appointmentReviewMapper;

    @Mock
    DoctorRepository doctorRepository;

    @Test
    void estimateAppointment_appointmentFound() {
        when(doctorAppointmentRepository.findById(APPOINTMENT_ID)).thenReturn(of(NOT_RATED_APPOINTMENT));
        when(appointmentReviewMapper.mapToDto(APPOINTMENT_REVIEW)).thenReturn(APPOINTMENT_REVIEW_DTO);

        assertEquals(appointmentReviewService.estimateAppointment(APPOINTMENT_ID, APPOINTMENT_MARK), APPOINTMENT_REVIEW_DTO);
    }

    @Test
    void estimateAppointment_appointmentNotFound() {
        when(doctorAppointmentRepository.findById(APPOINTMENT_ID)).thenReturn(empty());

        assertThrows(EntityNotFoundException.class,
                () -> appointmentReviewService.estimateAppointment(APPOINTMENT_ID, APPOINTMENT_MARK));
    }

    @Test
    void estimateAppointment_appointmentNotCompleted() {
        when(doctorAppointmentRepository.findById(APPOINTMENT_ID)).thenReturn(of(APPOINTMENT_SCHEDULED));

        assertThrows(InvalidStateException.class,
                () -> appointmentReviewService.estimateAppointment(APPOINTMENT_ID, APPOINTMENT_MARK));
    }

    @Test
    void doctorsRateProcess_ratedAppointment() {
        when(doctorRepository.findAll()).thenReturn(List.of(DOCTOR));

        appointmentReviewService.doctorsRateProcess();

        verify(doctorRepository, times(1)).save(DOCTOR);
    }

    @Test
    void doctorsRateProcess_notRatedAppointment() {
        when(doctorRepository.findAll()).thenReturn(List.of(DOCTOR_NOT_RATED));

        assertThrows(EntityNotFoundException.class, () -> appointmentReviewService.doctorsRateProcess());
    }

    private static DoctorAppointment createAppointment(Long id, Status status, AppointmentReview appointmentReview) {
        return DoctorAppointment.builder()
                .id(id)
                .status(status)
                .appointmentReview(appointmentReview)
                .build();
    }

    private static AppointmentReview createReview(Double mark, DoctorAppointment appointment) {
        return AppointmentReview.builder()
                .mark(mark)
                .doctorsAppointment(appointment)
                .build();
    }

    private static Doctor createDoctor(Long id, DoctorAppointment appointment) {
        Doctor doctor = new Doctor();
        doctor.setDoctorsAppointments(List.of(appointment));
        doctor.setId(id);

        return doctor;
    }
}