package com.blueTeam.medicalService.controller;

import com.blueTeam.medicalService.dto.user.doctor.DoctorRepresentationDto;
import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;
import com.blueTeam.medicalService.dto.user.patient.PatientRepresentationDto;
import com.blueTeam.medicalService.entity.enums.Status;
import com.blueTeam.medicalService.service.DoctorAppointmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.blueTeam.medicalService.entity.enums.Status.*;
import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DoctorAppointmentControllerTest {
    private static final DoctorAppointmentRepresentationDto FREE_APPOINTMENT_DTO = createAppointmentDto(1L,
            LocalDateTime.of(2024, 5, 1, 12, 0, 0), 1L, 1L, null);
    private static final DoctorAppointmentRepresentationDto SCHEDULED_APPOINTMENT_DTO = createAppointmentDto(1L,
            LocalDateTime.of(2024, 5, 1, 12, 0, 0), 1L, 1L, SCHEDULED);
    private static final DoctorAppointmentRepresentationDto CANCELED_APPOINTMENT_DTO = createAppointmentDto(1L,
            LocalDateTime.of(2024, 5, 1, 12, 0, 0), 1L, 1L, CANCELED);
    private static final Long DOCTOR_ID = 1L;
    private static final Long PATIENT_ID = 1L;
    private static final Long APPOINTMENT_ID = 1L;
    private static final LocalDate DATE = LocalDate.of(2024, 5, 1);
    private static final LocalDateTime DATE_TIME = LocalDateTime.of(2024, 5, 1, 12, 0, 0);


    @InjectMocks
    DoctorAppointmentController doctorAppointmentController;

    @Mock
    DoctorAppointmentService doctorAppointmentService;

    @Test
    void findAllFreeAppointmentsByDoctorIdAndDate() {
        when(doctorAppointmentService.findAllFreeAppointmentsByDoctorIdAndDate(DOCTOR_ID, DATE))
                .thenReturn(of(FREE_APPOINTMENT_DTO));

        List<DoctorAppointmentRepresentationDto> expected = of(FREE_APPOINTMENT_DTO);

        Assertions.assertEquals(expected, doctorAppointmentController.findAllFreeAppointmentsByDoctorIdAndDate(DOCTOR_ID, DATE));
    }

    @Test
    void createAppointment() {
        when(doctorAppointmentService.createAppointment(PATIENT_ID, DOCTOR_ID, DATE_TIME)).thenReturn(SCHEDULED_APPOINTMENT_DTO);

        assertEquals(doctorAppointmentController.createAppointment(PATIENT_ID, DOCTOR_ID, DATE_TIME), SCHEDULED_APPOINTMENT_DTO);
    }

    @Test
    void cancelAppointment() {
        when(doctorAppointmentService.cancelAppointment(APPOINTMENT_ID)).thenReturn(CANCELED_APPOINTMENT_DTO);

        assertEquals(doctorAppointmentController.cancelAppointment(APPOINTMENT_ID), CANCELED_APPOINTMENT_DTO);
    }

    private static PatientRepresentationDto createPatientDto(Long id) {
        return PatientRepresentationDto.builder()
                .id(id)
                .build();
    }

    private static DoctorRepresentationDto createDoctorDto(Long id) {
        return DoctorRepresentationDto.builder()
                .id(id)
                .build();
    }

    private static DoctorAppointmentRepresentationDto createAppointmentDto(Long doctorId,
                                                                           LocalDateTime dateTime,
                                                                           Long patientId,
                                                                           Long id,
                                                                           Status status) {
        return DoctorAppointmentRepresentationDto.builder()
                .doctorDto(createDoctorDto(doctorId))
                .dateTime(dateTime)
                .id(id)
                .status(status)
                .patientDto(createPatientDto(patientId))
                .build();
    }
}