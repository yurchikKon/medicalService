package com.blueTeam.medicalService.services.interfaces;


import com.blueTeam.medicalService.entities.AnalysisDirection;
import com.blueTeam.medicalService.entities.enums.Usage;

import java.util.List;

public interface AnalysisDirectionService {
    public List<AnalysisDirection> findAllByPatientidAndUsage(Long patientid, Usage usage);
}
