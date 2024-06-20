package com.blueTeam.medicalService.service;

import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;
import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionNamedDto;

import java.util.List;

public interface AnalysisDirectionService {
    AnalysisDirectionDto passAnalysis(Long id);
    AnalysisDirectionDto changeResultsAnalysisDirection(Long idAnalysisDirection, String newResult);
    List<AnalysisDirectionNamedDto> getUsedAnalysisRecords(Long id);
}
