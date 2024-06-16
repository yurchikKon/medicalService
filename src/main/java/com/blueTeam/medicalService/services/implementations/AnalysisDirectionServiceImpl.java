package com.blueTeam.medicalService.services.implementations;

import com.blueTeam.medicalService.entities.AnalysisDirection;
import com.blueTeam.medicalService.entities.enums.Usage;
import com.blueTeam.medicalService.repositories.AnalysisDirectionRepository;
import com.blueTeam.medicalService.services.interfaces.AnalysisDirectionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnalysisDirectionServiceImpl implements AnalysisDirectionService {

    private final AnalysisDirectionRepository analysisDirectionRepository;
    @Override
    public void changeResultsAnalysisDirection(Long idAnalysisDirection, String newResult) {
        AnalysisDirection analysisDirection = analysisDirectionRepository.findById(idAnalysisDirection)
                .orElseThrow(() -> new EntityNotFoundException("Invalid id: " + idAnalysisDirection));
            if(analysisDirection.getUsage()==Usage.USED){
                analysisDirection.setResult(newResult);
                analysisDirectionRepository.save(analysisDirection);
                log.info("Изменена запись в результатах анализа на: {}", newResult);
            }else log.info("Выбранный анализ имеет статус UNUSED.");
        }
}
