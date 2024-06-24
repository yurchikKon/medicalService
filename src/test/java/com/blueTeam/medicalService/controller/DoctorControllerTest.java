package com.blueTeam.medicalService.controller;

import com.blueTeam.medicalService.dto.user.doctor.DoctorRepresentationDto;
import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;
import com.blueTeam.medicalService.dto.user.patient.PatientRepresentationDto;
import com.blueTeam.medicalService.entity.enums.Status;
import com.blueTeam.medicalService.service.DoctorAppointmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.blueTeam.medicalService.entity.enums.Status.SCHEDULED;
import static java.time.LocalDateTime.of;
import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DoctorControllerTest {
    private static final Long DOCTOR_ID = 1L;
    private static final LocalDate DATE = LocalDate.of(2024, 5, 1);
    private static final DoctorAppointmentRepresentationDto SCHEDULED_APPOINTMENT_DTO = createAppointmentDto(1L,
            of(2024, 5, 1, 12, 0, 0), 1L, 1L, SCHEDULED);

    @InjectMocks
    DoctorController doctorController;

    @Mock
    DoctorAppointmentService doctorAppointmentService;

    @Test
    void findAllScheduledByDoctorIdAndDate() {
        when(doctorAppointmentService.findAllScheduledByDoctorIdAndDate(DOCTOR_ID, DATE))
                .thenReturn(of(SCHEDULED_APPOINTMENT_DTO));
        List<DoctorAppointmentRepresentationDto> expected = of(SCHEDULED_APPOINTMENT_DTO);

        assertEquals(doctorController.findAllScheduledByDoctorIdAndDate(DOCTOR_ID, DATE), expected);
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