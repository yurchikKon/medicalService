package com.blueTeam.medicalService.services.interfaces;


import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;

import java.util.List;

public interface PatientService {
    List<AnalysisDirectionDto> findActivePatientAnalysisDirections(Long patientId);

}
