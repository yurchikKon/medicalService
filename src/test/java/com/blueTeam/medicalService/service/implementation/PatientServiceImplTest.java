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
import java.util.stream.Stream;

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
    void findActivePatientAnalysisDirections_ShouldReturnResult() {
        Long patientId = 1L;
        var patient = createPatient(patientId);
        DoctorAppointment appointment = createAppointment(patient);
        AnalysisDirection direction = createAnalysisDirection(appointment);
        List<AnalysisDirectionDto> expectedDtos = Stream.of(direction)
                .map(t -> createDto(t.getId()))
                .toList();

        when(doctorAppointmentRepository.findAllByPatientIdAndStatus(patientId, Status.COMPLETED))
                .thenReturn(List.of(appointment));
        List.of(direction).forEach(t -> when(mapper.mapToDto(t)).thenReturn(createDto(t.getId())));

        List<AnalysisDirectionDto> actualDtos = patientService.findActivePatientAnalysisDirections(patientId);

        assertEquals(actualDtos, expectedDtos);
        verify(doctorAppointmentRepository, times(1)).findAllByPatientIdAndStatus(patientId, Status.COMPLETED);
        List.of(direction).forEach(t -> verify(mapper, times(1)).mapToDto(direction));
    }

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

    private DoctorAppointment createAppointment(Patient patient) {
        DoctorAppointment appointment = new DoctorAppointment();
        appointment.setPatient(patient);
        appointment.setStatus(Status.COMPLETED);
        return appointment;
    }

    private Patient createPatient(Long id) {
        Patient patient = new Patient();
        patient.setId(id);
        return patient;
    }

    private AnalysisDirection createAnalysisDirection(DoctorAppointment appointment) {
        AnalysisDirection direction = new AnalysisDirection();
        direction.setId(1L);
        direction.setStatus(DirectionStatus.VALID);
        direction.setDoctorsAppointment(appointment);
        appointment.setAnalysisDirections(List.of(direction));
        return direction;
    }

    private AnalysisDirectionDto createDto(Long id) {
        return new AnalysisDirectionDto(id, DirectionStatus.VALID, Usage.UNUSED, null);
    }
}
