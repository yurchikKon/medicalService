package com.blueTeam.medicalService.service;


import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;

import java.util.List;

public interface PatientService {
    List<AnalysisDirectionDto> findActivePatientAnalysisDirections(Long patientId);

}
