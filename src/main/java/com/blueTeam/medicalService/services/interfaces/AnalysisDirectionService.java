package com.blueTeam.medicalService.services.interfaces;

import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;
import com.blueTeam.medicalService.entities.AnalysisDirection;

public interface AnalysisDirectionService {
    AnalysisDirectionDto passAnalysis(Long id);
}
