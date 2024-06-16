package com.blueTeam.medicalService.services.implementations;

import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;
import com.blueTeam.medicalService.entities.AnalysisDirection;
import com.blueTeam.medicalService.entities.enums.Usage;
import com.blueTeam.medicalService.repositories.AnalysisDirectionRepository;
import com.blueTeam.medicalService.services.interfaces.AnalysisDirectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnalysisDirectionServiceImpl implements AnalysisDirectionService {

    public final AnalysisDirectionRepository analysisDirectionRepository;

    @Override
    public List<AnalysisDirection> findAllByPatientidAndUsage(Long patientid) {
        List<AnalysisDirection> analysisDirectionList = analysisDirectionRepository.findAllByPatientidAndUsage(patientid);
        return analysisDirectionList;
    }
}