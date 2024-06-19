package com.blueTeam.medicalService.service.implementation;

import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;
import com.blueTeam.medicalService.entity.AnalysisDirection;
import com.blueTeam.medicalService.exception.InvalidStateException;
import com.blueTeam.medicalService.exception.ResourceAlreadyExistException;
import com.blueTeam.medicalService.mapper.AnalysisDirectionMapper;
import com.blueTeam.medicalService.repository.AnalysisDirectionRepository;
import com.blueTeam.medicalService.service.interfaces.AnalysisDirectionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.blueTeam.medicalService.entity.enums.DirectionStatus.*;
import static com.blueTeam.medicalService.entity.enums.Usage.*;

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

    @Override
    public AnalysisDirectionDto changeResultsAnalysisDirection(Long idAnalysisDirection, String newResult) {
        AnalysisDirection analysisDirection = analysisDirectionRepository.findById(idAnalysisDirection)
                .orElseThrow(() -> new EntityNotFoundException("Invalid id: " + idAnalysisDirection));
            if(analysisDirection.getUsage().equals(USED)){
                analysisDirection.setResult(newResult);
                analysisDirectionRepository.save(analysisDirection);
                log.info("Изменена запись в результатах анализа на: {}", newResult);

                return analysisDirectionMapper.mapToDto(analysisDirection);
            }
            else {
                throw new InvalidStateException("Analysis has not been passed yet");
            }
        }
}