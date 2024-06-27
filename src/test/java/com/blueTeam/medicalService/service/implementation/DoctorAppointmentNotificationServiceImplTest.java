package com.blueTeam.medicalService.service.implementation;

import com.blueTeam.medicalService.service.DoctorAppointmentService;
import com.blueTeam.medicalService.service.EmailSender;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DoctorAppointmentNotificationServiceImplTest {

    @Mock
    private DoctorAppointmentService appointmentService;

    @Mock
    private EmailSender emailSenderImpl;

    @InjectMocks
    private DoctorAppointmentNotificationServiceImpl service;

    @Test
    void notifyPatients_ShouldNotSendEmailNotificationWhenThereAreNoPlannedAppointments() throws MessagingException {
        when(appointmentService.getPlannedAppointmentsForNotification()).thenReturn(Collections.emptyList());

        service.notifyPatients();

        verify(emailSenderImpl, never()).sendMessage(anyString(), anyString(), any());
        verify(appointmentService, never()).saveAllAppointments(any());
    }

}
