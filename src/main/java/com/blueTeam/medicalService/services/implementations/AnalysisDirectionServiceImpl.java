package com.blueTeam.medicalService.services.implementations;

import com.blueTeam.medicalService.entities.AnalysisDirection;
import com.blueTeam.medicalService.entities.enums.DirectionStatus;
import com.blueTeam.medicalService.entities.enums.Usage;
import com.blueTeam.medicalService.exceptions.ResourceAlreadyExistException;
import com.blueTeam.medicalService.repositories.AnalysisDirectionRepository;
import com.blueTeam.medicalService.services.interfaces.AnalysisDirectionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.blueTeam.medicalService.entities.enums.DirectionStatus.*;
import static com.blueTeam.medicalService.entities.enums.Usage.*;

@Service
@RequiredArgsConstructor
public class AnalysisDirectionServiceImpl implements AnalysisDirectionService {
    private final AnalysisDirectionRepository analysisDirectionRepository;
    @Override
    public AnalysisDirection signUpForAnalysisDirection(Long id) {
        AnalysisDirection analysisDirection = analysisDirectionRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("No direction with such id "));
        if(analysisDirection.getStatus().equals(VALID)) {
            analysisDirection.setStatus(INVALID);
            analysisDirection.setUsage(USED);

            return analysisDirection;
        }
        else {
            throw new ResourceAlreadyExistException("Analysis has already bin passed");
        }
    }
}
