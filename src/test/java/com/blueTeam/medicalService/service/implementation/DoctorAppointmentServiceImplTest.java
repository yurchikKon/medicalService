package com.blueTeam.medicalService.service.implementation;

import com.blueTeam.medicalService.entity.DoctorAppointment;
import com.blueTeam.medicalService.entity.enums.Notification;
import com.blueTeam.medicalService.entity.enums.Status;
import com.blueTeam.medicalService.repository.DoctorAppointmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DoctorAppointmentServiceImplTest {

    @Mock
    private DoctorAppointmentRepository doctorAppointmentRepository;

    @InjectMocks
    private DoctorAppointmentServiceImpl doctorAppointmentService;

    @Test
    void getPlannedAppointmentsForNotification_ShouldReturnPlannedAppointments() {
        var expectedAppointments = getExpectedAppointments();

        when(doctorAppointmentRepository.findPlannedAppointments(Status.SCHEDULED, Notification.PLANED))
                .thenReturn(expectedAppointments);

        var actualAppointments = doctorAppointmentService.getPlannedAppointmentsForNotification();

        assertEquals(expectedAppointments, actualAppointments);
    }

    @Test
    void getPlannedAppointmentsForNotification_ShouldReturnEmptyListIfNoneFound() {
        when(doctorAppointmentRepository.findPlannedAppointments(Status.SCHEDULED, Notification.PLANED)).thenReturn(null);

        var actualAppointments = doctorAppointmentService.getPlannedAppointmentsForNotification();

        assertEquals(Collections.emptyList(), actualAppointments);
    }

    @Test
    void saveAllAppointments_ShouldReturnSavedAppointments() {
        var expectedAppointments = getExpectedAppointments();
        when(doctorAppointmentRepository.saveAll(expectedAppointments)).thenReturn(expectedAppointments);

        var actualAppointments = doctorAppointmentService.saveAllAppointments(expectedAppointments);

        assertEquals(expectedAppointments, actualAppointments);
    }

    private List<DoctorAppointment> getExpectedAppointments() {
        DoctorAppointment appointment1 = new DoctorAppointment();
        appointment1.setStatus(Status.SCHEDULED);
        appointment1.setNotification(Notification.PLANED);

        DoctorAppointment appointment2 = new DoctorAppointment();
        appointment2.setStatus(Status.SCHEDULED);
        appointment2.setNotification(Notification.PLANED);

        return Arrays.asList(appointment1, appointment2);
    }
}
