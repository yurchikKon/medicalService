package com.blueTeam.medicalService.services.implementations;

import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;
import com.blueTeam.medicalService.entities.AnalysisDirection;
import com.blueTeam.medicalService.entities.enums.DirectionStatus;
import com.blueTeam.medicalService.entities.enums.Usage;
import com.blueTeam.medicalService.exceptions.ResourceAlreadyExistException;
import com.blueTeam.medicalService.mapper.AnalysisDirectionMapper;
import com.blueTeam.medicalService.repositories.AnalysisDirectionRepository;
import com.blueTeam.medicalService.services.interfaces.AnalysisDirectionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.blueTeam.medicalService.entities.enums.DirectionStatus.*;
import static com.blueTeam.medicalService.entities.enums.Usage.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnalysisDirectionServiceImpl implements AnalysisDirectionService {
    private final AnalysisDirectionRepository analysisDirectionRepository;
    private final AnalysisDirectionMapper analysisDirectionMapper;

    @Override
    @Transactional
    public AnalysisDirectionDto passAnalysis(Long id) {
        AnalysisDirection analysisDirection = analysisDirectionRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("No direction with such id "));
        if (analysisDirection.getStatus().equals(VALID)) {
            analysisDirection.setStatus(INVALID);
            analysisDirection.setUsage(USED);
            analysisDirectionRepository.save(analysisDirection);
            log.info("Analysis direction with id {} was used", id);

            return analysisDirectionMapper.mapToDto(analysisDirection);
        } else {
            throw new ResourceAlreadyExistException("Analysis has already been passed");
        }
    }

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
