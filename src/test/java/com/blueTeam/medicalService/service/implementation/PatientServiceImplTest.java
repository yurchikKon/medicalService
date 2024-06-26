package com.blueTeam.medicalService.service.implementation;

import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;
import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;
import com.blueTeam.medicalService.entity.AnalysisDirection;
import com.blueTeam.medicalService.entity.DoctorAppointment;
import com.blueTeam.medicalService.entity.Patient;
import com.blueTeam.medicalService.entity.enums.DirectionStatus;
import com.blueTeam.medicalService.entity.enums.Status;
import com.blueTeam.medicalService.entity.enums.Usage;
import com.blueTeam.medicalService.mapper.AnalysisDirectionMapper;
import com.blueTeam.medicalService.mapper.DoctorAppointmentMapper;
import com.blueTeam.medicalService.repository.DoctorAppointmentRepository;
import com.blueTeam.medicalService.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.blueTeam.medicalService.entity.enums.DirectionStatus.*;
import static com.blueTeam.medicalService.entity.enums.Status.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {

    private static final Long PATIENT_ID = 1L;
    private static final AnalysisDirection ANALYSIS_DIRECTION_ONE = createAnalysisDirection(1L, VALID);
    private static final AnalysisDirection ANALYSIS_DIRECTION_TWO = createAnalysisDirection(2L, INVALID);
    private static final List<AnalysisDirection> ANALYSIS_DIRECTION_LIST = fillAnalysisDirectionList();
    private static final DoctorAppointment APPOINTMENT_ONE = createAppointment(1L, COMPLETED, ANALYSIS_DIRECTION_LIST);
    private static final DoctorAppointment APPOINTMENT_TWO = createAppointment(2L, COMPLETED, new ArrayList<>());
    private static final DoctorAppointment APPOINTMENT_THREE = createAppointment(3L, SCHEDULED, ANALYSIS_DIRECTION_LIST);
    private static final AnalysisDirectionDto ANALYSIS_DIRECTION_DTO = createAnalysisDirectionDto(1L, VALID);
    private static final DoctorAppointmentRepresentationDto DOCTOR_APPOINTMENT_DTO = createDoctorAppointmentDto(3L, SCHEDULED);

    @InjectMocks
    PatientServiceImpl patientService;

    @Mock
    DoctorAppointmentRepository doctorAppointmentRepository;

    @Mock
    AnalysisDirectionMapper analysisDirectionMapper;

    @Mock
    DoctorAppointmentMapper doctorAppointmentMapper;

    @Test
    void findActivePatientAnalysisDirections() {
        when(doctorAppointmentRepository.findAllByPatientIdAndStatus(PATIENT_ID, COMPLETED))
                .thenReturn(List.of(APPOINTMENT_ONE, APPOINTMENT_TWO));
        when(analysisDirectionMapper.mapToDto(ANALYSIS_DIRECTION_ONE)).thenReturn(ANALYSIS_DIRECTION_DTO);

        assertEquals(List.of(ANALYSIS_DIRECTION_DTO), patientService.findActivePatientAnalysisDirections(PATIENT_ID));
    }

    @Test
    void findActivePatientAppointments() {
        when(doctorAppointmentRepository.findAllByPatientIdAndStatus(PATIENT_ID, SCHEDULED))
                .thenReturn(List.of(APPOINTMENT_THREE));
        when(doctorAppointmentMapper.mapToDto(APPOINTMENT_THREE)).thenReturn(DOCTOR_APPOINTMENT_DTO);

       assertEquals(List.of(DOCTOR_APPOINTMENT_DTO), patientService.findActivePatientAppointments(PATIENT_ID));
    }

    private static DoctorAppointment createAppointment(Long id, Status status, List<AnalysisDirection> analysisDirectionList){
        return DoctorAppointment.builder().id(id).status(status).analysisDirections(analysisDirectionList).build();
    }

    private static List<AnalysisDirection> fillAnalysisDirectionList() {
        List<AnalysisDirection> analysisDirectionList = new ArrayList<>();
        analysisDirectionList.add(ANALYSIS_DIRECTION_ONE);
        analysisDirectionList.add(ANALYSIS_DIRECTION_TWO);
        return analysisDirectionList;
    }

    private static AnalysisDirection createAnalysisDirection(Long id, DirectionStatus status) {
        return AnalysisDirection.builder().id(id).status(status).build();
    }

    private static AnalysisDirectionDto createAnalysisDirectionDto(Long id, DirectionStatus directionStatus) {
        return AnalysisDirectionDto.builder().id(id).status(directionStatus).build();
    }

    private static DoctorAppointmentRepresentationDto createDoctorAppointmentDto(Long id, Status status) {
        return DoctorAppointmentRepresentationDto.builder().id(id).status(status).build();
    }

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