package com.blueTeam.medicalService.controllers;

import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;
import com.blueTeam.medicalService.repositories.AnalysisDirectionRepository;
import com.blueTeam.medicalService.services.interfaces.AnalysisDirectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/analysisDirections")
public class AnalysisDirectionController {
    private final AnalysisDirectionService analysisDirectionService;

    @PutMapping("/{id}/usage")
    public AnalysisDirectionDto passAnalysis(@PathVariable Long id) {
        return analysisDirectionService.passAnalysis(id);
    }
}
