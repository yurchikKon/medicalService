package com.blueTeam.medicalService.services.interfaces;


<<<<<<< HEAD
import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;
=======
>>>>>>> master
import com.blueTeam.medicalService.entities.AnalysisDirection;
import com.blueTeam.medicalService.entities.enums.Usage;

import java.util.List;
<<<<<<< HEAD
import java.util.UUID;

public interface AnalysisDirectionService {
    public List<AnalysisDirectionDto> findAllByPatientidAndUsage(Long patientid);
=======

public interface AnalysisDirectionService {
    void changeResultsAnalysisDirection(Long idAnalysisDirection, String newResult);
>>>>>>> master
}
