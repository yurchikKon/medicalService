package com.blueTeam.medicalService.service.interfaces;

import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;

public interface AnalysisDirectionService {
    AnalysisDirectionDto passAnalysis(Long id);
    AnalysisDirectionDto changeResultsAnalysisDirection(Long idAnalysisDirection, String newResult);
}