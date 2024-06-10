package com.blueTeam.medicalService.services.interfaces;


import com.blueTeam.medicalService.entities.AnalysisDirection;

import java.util.List;

public interface AnalysisDirectionService {
    public void changeResultsAnalysisDirection(Long idAnalysisDirection, String newResult);
}
