package com.blueTeam.medicalService.service.implementation;

import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;
import com.blueTeam.medicalService.entity.AnalysisDirection;
import com.blueTeam.medicalService.entity.DoctorAppointment;
import com.blueTeam.medicalService.entity.Patient;
import com.blueTeam.medicalService.entity.enums.DirectionStatus;
import com.blueTeam.medicalService.entity.enums.Status;
import com.blueTeam.medicalService.entity.enums.Usage;
import com.blueTeam.medicalService.mapper.AnalysisDirectionMapper;
import com.blueTeam.medicalService.repository.DoctorAppointmentRepository;
import com.blueTeam.medicalService.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {

    @Mock
    private DoctorAppointmentRepository doctorAppointmentRepository;
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private AnalysisDirectionMapper mapper;

    @InjectMocks
    private PatientServiceImpl patientService;

    @Test
    void isPatientPresent_ShouldReturnTrue() {
        Long patientId = 1L;
        when(patientRepository.findById(any(Long.class))).thenReturn(Optional.of(new Patient()));

        boolean isPatientPresent = patientService.isPatientPresent(patientId);

        assertTrue(isPatientPresent);
        verify(patientRepository, times(1)).findById(patientId);
    }

    @Test
    void isPatientPresent_ShouldReturnFalse() {
        Long patientId = 1L;
        when(patientRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        boolean isPatientPresent = patientService.isPatientPresent(patientId);

        assertFalse(isPatientPresent);
        verify(patientRepository, times(1)).findById(patientId);
    }

}
