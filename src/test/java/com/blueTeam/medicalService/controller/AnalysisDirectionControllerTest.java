package com.blueTeam.medicalService.controller;

import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;
import com.blueTeam.medicalService.service.AnalysisDirectionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.blueTeam.medicalService.entity.enums.DirectionStatus.INVALID;
import static com.blueTeam.medicalService.entity.enums.Usage.USED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnalysisDirectionControllerTest {
    private static final AnalysisDirectionDto ANALYSIS_DIRECTION_DTO = new AnalysisDirectionDto(1L, INVALID, USED, "");
    private static final Long ANALYSIS_DIRECTION_ID = 1L;

    @InjectMocks
    AnalysisDirectionController analysisDirectionController;

    @Mock
    AnalysisDirectionService analysisDirectionService;

    @Test
    void passAnalysis() {
        when(analysisDirectionService.passAnalysis(ANALYSIS_DIRECTION_ID)).thenReturn(ANALYSIS_DIRECTION_DTO);

        assertEquals(analysisDirectionController.passAnalysis(ANALYSIS_DIRECTION_ID), ANALYSIS_DIRECTION_DTO);
    }
}