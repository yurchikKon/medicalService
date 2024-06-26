package com.blueTeam.medicalService.controller;

import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;
import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;
import com.blueTeam.medicalService.entity.enums.DirectionStatus;
import com.blueTeam.medicalService.entity.enums.Status;
import com.blueTeam.medicalService.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.blueTeam.medicalService.entity.enums.DirectionStatus.*;
import static com.blueTeam.medicalService.entity.enums.Status.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientControllerTest {
    private static final Long PATIENT_ID = 1L;
    private static final AnalysisDirectionDto ANALYSIS_DIRECTION_DTO = createAnalysisDirectionDto(1L, VALID);
    private static final DoctorAppointmentRepresentationDto DOCTOR_APPOINTMENT_DTO = createDoctorAppointmentDto(1L, SCHEDULED);

    @InjectMocks
    PatientController patientController;

    @Mock
    PatientService patientService;

    @Test
    void findActivePatientAnalysisDirections() {
        when(patientService.findActivePatientAnalysisDirections(PATIENT_ID))
                .thenReturn(List.of(ANALYSIS_DIRECTION_DTO));
        assertEquals(List.of(ANALYSIS_DIRECTION_DTO), patientController.findActivePatientAnalysisDirections(PATIENT_ID));
    }

    @Test
    void findActivePatientAppointments() {
        when(patientService.findActivePatientAppointments(PATIENT_ID)).thenReturn(List.of(DOCTOR_APPOINTMENT_DTO));
        assertEquals(List.of(DOCTOR_APPOINTMENT_DTO), patientController.findActivePatientAppointments(PATIENT_ID));
    }

    private static AnalysisDirectionDto createAnalysisDirectionDto(Long id, DirectionStatus status) {
        return AnalysisDirectionDto.builder()
                .id(id)
                .status(status)
                .build();
    }

    private static DoctorAppointmentRepresentationDto createDoctorAppointmentDto(Long id, Status status) {
        return DoctorAppointmentRepresentationDto.builder()
                .id(id)
                .status(status)
                .build();
    }
}