package com.blueTeam.medicalService.service.implementation;

import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;
import com.blueTeam.medicalService.entity.AnalysisDirection;
import com.blueTeam.medicalService.entity.enums.DirectionStatus;
import com.blueTeam.medicalService.entity.enums.Usage;
import com.blueTeam.medicalService.exception.ResourceAlreadyExistException;
import com.blueTeam.medicalService.mapper.AnalysisDirectionMapper;
import com.blueTeam.medicalService.repository.AnalysisDirectionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.blueTeam.medicalService.entity.enums.DirectionStatus.INVALID;
import static com.blueTeam.medicalService.entity.enums.DirectionStatus.VALID;
import static com.blueTeam.medicalService.entity.enums.Usage.UNUSED;
import static com.blueTeam.medicalService.entity.enums.Usage.USED;
import static java.util.Optional.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

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
}