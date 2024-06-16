package com.blueTeam.medicalService.services.interfaces;


import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;
import com.blueTeam.medicalService.entities.AnalysisDirection;
import com.blueTeam.medicalService.entities.enums.Usage;

import java.util.List;
import java.util.UUID;

public interface AnalysisDirectionService {
    public List<AnalysisDirection> findAllByPatientidAndUsage(Long patientid);
}
