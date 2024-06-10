package com.blueTeam.medicalService.services.implementations;

import com.blueTeam.medicalService.entities.AnalysisDirection;
import com.blueTeam.medicalService.entities.enums.Usage;
import com.blueTeam.medicalService.repositories.AnalysisDirectionRepository;
import com.blueTeam.medicalService.services.interfaces.AnalysisDirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalysisDirectionServiceImpl implements AnalysisDirectionService {
    @Autowired
    private AnalysisDirectionRepository analysisDirectionRepository;
    @Override
    public void changeResultsAnalysisDirection(Long idAnalysisDirection, String newResult) {
        AnalysisDirection analysisDirection = analysisDirectionRepository.findById(idAnalysisDirection).get();
        if(analysisDirection.getUsage()==Usage.USED){
            analysisDirection.setResult(newResult);
        }
        analysisDirectionRepository.save(analysisDirection);
    }
}
