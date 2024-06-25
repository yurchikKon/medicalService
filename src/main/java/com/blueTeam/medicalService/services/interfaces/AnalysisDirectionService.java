package com.blueTeam.medicalService.services.interfaces;


import com.blueTeam.medicalService.entities.AnalysisDirection;
import com.blueTeam.medicalService.entities.enums.Usage;

import java.util.List;

public interface AnalysisDirectionService {
    void changeResultsAnalysisDirection(Long idAnalysisDirection, String newResult);
}
