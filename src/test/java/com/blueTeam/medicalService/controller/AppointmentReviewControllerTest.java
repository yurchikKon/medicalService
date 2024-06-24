package com.blueTeam.medicalService.controller;

import com.blueTeam.medicalService.dto.user.doctor.appointment.AppointmentReviewDto;
import com.blueTeam.medicalService.service.AppointmentReviewService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppointmentReviewControllerTest {
    private static final AppointmentReviewDto APPOINTMENT_REVIEW_DTO = new AppointmentReviewDto(1L, 5.00);
    private static final Long APPOINTMENT_ID = 1L;
    private static final Double APPOINTMENT_MARK = 5.00;

    @InjectMocks
    AppointmentReviewController appointmentReviewController;

    @Mock
    AppointmentReviewService appointmentReviewService;

    @Test
    void rateAppointment() {
        when(appointmentReviewService.estimateAppointment(APPOINTMENT_ID, APPOINTMENT_MARK)).thenReturn(APPOINTMENT_REVIEW_DTO);

        assertEquals(appointmentReviewController.rateAppointment(APPOINTMENT_ID,APPOINTMENT_MARK), APPOINTMENT_REVIEW_DTO);

    }
}