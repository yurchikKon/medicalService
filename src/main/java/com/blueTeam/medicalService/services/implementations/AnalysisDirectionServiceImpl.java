package com.blueTeam.medicalService.services.implementations;

import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;
import com.blueTeam.medicalService.mapper.AnalysisDirectionMapper;
import com.blueTeam.medicalService.repository.AnalysisDirectionRepository;
import com.blueTeam.medicalService.services.interfaces.AnalysisDirectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class AnalysisDirectionServiceImpl implements AnalysisDirectionService {

    private final AnalysisDirectionRepository analysisDirectionRepository;
    private final AnalysisDirectionMapper analysisDirectionMapper;

    @Override
    public List<AnalysisDirectionDto> findAllByPatientidAndUsage(Long patientId) {
        return analysisDirectionRepository.findAllByPatientidAndUsage(patientId).stream()
                .map(analysisDirectionMapper::mapToDto)
                .toList();
    }
}