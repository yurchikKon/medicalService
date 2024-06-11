package com.blueTeam.medicalService.services.implementations;

import com.blueTeam.medicalService.entities.AnalysisDirection;
import com.blueTeam.medicalService.entities.enums.Usage;
import com.blueTeam.medicalService.repositories.AnalysisDirectionRepository;
import com.blueTeam.medicalService.repositories.PatientRepository;
import com.blueTeam.medicalService.services.interfaces.AnalysisDirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalysisDirectionServiceImpl implements AnalysisDirectionService {
    @Autowired
    AnalysisDirectionRepository analysisDirectionRepository;
    PatientRepository patientRepository;
    @Override
    public List<AnalysisDirection> findAllByPatientidAndUsage(Long patientid, Usage usage) {
        List<AnalysisDirection> analysisDirectionList = patientRepository.findAllByPatientidAndUsage(patientid, Usage.USED);
        return analysisDirectionList;
    }
}