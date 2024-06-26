package com.blueTeam.medicalService.service.implementation;

import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;
import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionNamedDto;
import com.blueTeam.medicalService.entity.AnalysisDirection;
import com.blueTeam.medicalService.entity.enums.DirectionStatus;
import com.blueTeam.medicalService.entity.enums.Usage;
import com.blueTeam.medicalService.exception.InvalidStateException;
import com.blueTeam.medicalService.exception.ResourceAlreadyExistException;
import com.blueTeam.medicalService.mapper.AnalysisDirectionMapper;
import com.blueTeam.medicalService.mapper.AnalysisDirectionNamedMapper;
import com.blueTeam.medicalService.repository.AnalysisDirectionRepository;
import com.blueTeam.medicalService.service.PatientService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.blueTeam.medicalService.entity.enums.DirectionStatus.INVALID;
import static com.blueTeam.medicalService.entity.enums.DirectionStatus.VALID;
import static com.blueTeam.medicalService.entity.enums.Usage.UNUSED;
import static com.blueTeam.medicalService.entity.enums.Usage.USED;
import static java.util.Optional.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class AnalysisDirectionServiceImplTest {
    private static final AnalysisDirectionDto ANALYSIS_DIRECTION_DTO = new AnalysisDirectionDto(1L, INVALID, USED, "");
    private static final Long ANALYSIS_DIRECTION_ID = 1L;
    private static final AnalysisDirection ANALYSIS_DIRECTION_UNUSED = createAnalysisDirection(1L, VALID, UNUSED);
    private static final AnalysisDirection ANALYSIS_DIRECTION_USED = createAnalysisDirection(1L, INVALID, USED);

    @InjectMocks
    AnalysisDirectionServiceImpl analysisDirectionService;

    @Mock
    AnalysisDirectionMapper analysisDirectionMapper;

    @Mock
    AnalysisDirectionRepository analysisDirectionRepository;

    @Test
    void passAnalysis_userFound() {
        when(analysisDirectionRepository.findById(ANALYSIS_DIRECTION_ID)).thenReturn(of(ANALYSIS_DIRECTION_UNUSED));
        when(analysisDirectionMapper.mapToDto(ANALYSIS_DIRECTION_USED)).thenReturn(ANALYSIS_DIRECTION_DTO);

        assertEquals(ANALYSIS_DIRECTION_DTO, analysisDirectionService.passAnalysis(ANALYSIS_DIRECTION_ID));
    }

    @Test
    void passAnalysis_userNotFound() {
        when(analysisDirectionRepository.findById(ANALYSIS_DIRECTION_ID)).thenReturn(empty());
        assertThrows(EntityNotFoundException.class, () -> analysisDirectionService.passAnalysis(ANALYSIS_DIRECTION_ID));
    }

    @Test
    void passAnalysis_invalidDirection() {
        when(analysisDirectionRepository.findById(ANALYSIS_DIRECTION_ID)).thenReturn(of(ANALYSIS_DIRECTION_USED));
        assertThrows(ResourceAlreadyExistException.class, () -> analysisDirectionService.passAnalysis(ANALYSIS_DIRECTION_ID));
    }

    private static AnalysisDirection createAnalysisDirection(Long id, DirectionStatus status, Usage usage) {
        return AnalysisDirection.builder()
                .id(id)
                .usage(usage)
                .status(status)
                .build();
    }

    @Mock
    private AnalysisDirectionRepository analysisDirectionRepository;
    @Mock
    private AnalysisDirectionMapper analysisDirectionMapper;
    @Mock
    private AnalysisDirectionNamedMapper analysisDirectionNamedMapper;
    @Mock
    private PatientService patientService;

    @InjectMocks
    private AnalysisDirectionServiceImpl analysisDirectionService;

    @Test
    void testGetUsedAnalysisRecords_ValidPatient() {
        Long patientId = 1L;
        List<AnalysisDirection> testDirections = createTestAppointments();
        List<AnalysisDirectionNamedDto> expectedDtos = testDirections.stream()
                .map(t -> createNamedDto(t.getId()))
                .toList();

        when(patientService.isPatientPresent(patientId)).thenReturn(true);
        when(analysisDirectionRepository.findUnusedAnalysisByPatientId(patientId, Usage.USED))
                .thenReturn(testDirections);
        testDirections.forEach(t -> when(analysisDirectionNamedMapper.mapToNamedDto(t, t.getAnalysis()))
                .thenReturn(createNamedDto(t.getId())));

        List<AnalysisDirectionNamedDto> actualDtos = analysisDirectionService.getUsedAnalysisRecords(patientId);

        assertThat(actualDtos).isEqualTo(expectedDtos);
        verify(patientService, times(1)).isPatientPresent(patientId);
        verify(analysisDirectionRepository, times(1)).findUnusedAnalysisByPatientId(patientId, Usage.USED);
        testDirections.forEach(t -> verify(analysisDirectionNamedMapper, times(1)).mapToNamedDto(t, t.getAnalysis()));
    }

    @Test
    void testGetUsedAnalysisRecords_InvalidPatient() {
        Long patientId = 1L;
        when(patientService.isPatientPresent(patientId)).thenReturn(false);

        assertThatThrownBy(() -> analysisDirectionService.getUsedAnalysisRecords(patientId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Invalid patient id: " + patientId);
        verify(patientService, times(1)).isPatientPresent(patientId);
        verifyNoInteractions(analysisDirectionRepository);
        verifyNoInteractions(analysisDirectionNamedMapper);
    }

    @Test
    void testPassAnalysis_ValidAnalysisDirection() {
        Long analysisDirectionId = 1L;
        AnalysisDirection analysisDirection = createValidAnalysisDirection(analysisDirectionId);
        AnalysisDirectionDto expectedDto = createDto(analysisDirectionId);

        when(analysisDirectionRepository.findById(analysisDirectionId)).thenReturn(Optional.of(analysisDirection));
        when(analysisDirectionMapper.mapToDto(analysisDirection)).thenReturn(expectedDto);

        AnalysisDirectionDto actualDto = analysisDirectionService.passAnalysis(analysisDirectionId);

        assertThat(actualDto).isEqualTo(expectedDto);
        verify(analysisDirectionRepository, times(1)).findById(analysisDirectionId);
        verify(analysisDirectionRepository, times(1)).save(analysisDirection);
        verify(analysisDirectionMapper, times(1)).mapToDto(analysisDirection);
    }

    @Test
    void testPassAnalysis_InvalidAnalysisDirection() {
        Long analysisDirectionId = 1L;
        AnalysisDirection analysisDirection = createInvalidAnalysisDirection(analysisDirectionId);

        when(analysisDirectionRepository.findById(analysisDirectionId)).thenReturn(Optional.of(analysisDirection));

        assertThatThrownBy(() -> analysisDirectionService.passAnalysis(analysisDirectionId))
                .isInstanceOf(ResourceAlreadyExistException.class)
                .hasMessage("Analysis has already been passed");
        verify(analysisDirectionRepository, times(1)).findById(analysisDirectionId);
        verifyNoMoreInteractions(analysisDirectionRepository, analysisDirectionMapper);
    }

    @Test
    void testChangeResultsAnalysisDirection_AnalysisDirectionPassed() {
        Long analysisDirectionId = 1L;
        String newResult = "New Result";
        AnalysisDirection analysisDirection = createUsedAnalysisDirection(analysisDirectionId);
        analysisDirection.setResult(newResult);
        AnalysisDirectionDto expectedDto = createDto(analysisDirectionId);

        when(analysisDirectionRepository.findById(analysisDirectionId)).thenReturn(Optional.of(analysisDirection));
        when(analysisDirectionMapper.mapToDto(analysisDirection)).thenReturn(expectedDto);

        AnalysisDirectionDto actualDto = analysisDirectionService.changeResultsAnalysisDirection(analysisDirectionId, newResult);

        assertThat(actualDto).isEqualTo(expectedDto);
        verify(analysisDirectionRepository, times(1)).findById(analysisDirectionId);
        verify(analysisDirectionRepository, times(1)).save(analysisDirection);
        verify(analysisDirectionMapper, times(1)).mapToDto(analysisDirection);
    }

    @Test
    void testChangeResultsAnalysisDirection_AnalysisDirectionNotPassed() {
        Long analysisDirectionId = 1L;
        String newResult = "New Result";
        AnalysisDirection analysisDirection = createValidAnalysisDirection(analysisDirectionId);

        when(analysisDirectionRepository.findById(analysisDirectionId)).thenReturn(Optional.of(analysisDirection));

        assertThatThrownBy(() -> analysisDirectionService.changeResultsAnalysisDirection(analysisDirectionId, newResult))
                .isInstanceOf(InvalidStateException.class)
                .hasMessage("Analysis has not been passed yet");
        verify(analysisDirectionRepository, times(1)).findById(analysisDirectionId);
        verifyNoMoreInteractions(analysisDirectionRepository, analysisDirectionMapper);
    }

    private List<AnalysisDirection> createTestAppointments() {
        AnalysisDirection testAppointment1 = new AnalysisDirection();
        testAppointment1.setId(1L);
        testAppointment1.setStatus(DirectionStatus.INVALID);
        testAppointment1.setUsage(Usage.USED);

        AnalysisDirection testAppointment2 = new AnalysisDirection();
        testAppointment2.setId(2L);
        testAppointment2.setStatus(DirectionStatus.VALID);
        testAppointment2.setUsage(Usage.USED);

        return List.of(testAppointment1, testAppointment2);
    }

    private AnalysisDirection createValidAnalysisDirection(Long id) {
        AnalysisDirection analysisDirection = new AnalysisDirection();
        analysisDirection.setId(id);
        analysisDirection.setStatus(DirectionStatus.VALID);
        analysisDirection.setUsage(Usage.UNUSED);
        return analysisDirection;
    }

    private AnalysisDirection createInvalidAnalysisDirection(Long id) {
        AnalysisDirection analysisDirection = new AnalysisDirection();
        analysisDirection.setId(id);
        analysisDirection.setStatus(DirectionStatus.INVALID);
        analysisDirection.setUsage(Usage.USED);
        return analysisDirection;
    }

    private AnalysisDirection createUsedAnalysisDirection(Long id) {
        AnalysisDirection analysisDirection = new AnalysisDirection();
        analysisDirection.setId(id);
        analysisDirection.setStatus(DirectionStatus.VALID);
        analysisDirection.setUsage(Usage.USED);
        return analysisDirection;
    }

    private AnalysisDirectionNamedDto createNamedDto(Long id) {
        return new AnalysisDirectionNamedDto(id, DirectionStatus.VALID, Usage.UNUSED, null,"Analisys");
    }

    private AnalysisDirectionDto createDto(Long id) {
        return new AnalysisDirectionDto(id, DirectionStatus.VALID, Usage.UNUSED, null);
    }
}