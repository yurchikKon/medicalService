package com.blueTeam.medicalService.controllers;

import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;
import com.blueTeam.medicalService.services.interfaces.AnalysisDirectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/analysisDirections")
public class AnalysisDirectionController {
    private final AnalysisDirectionService analysisDirectionService;

    @PutMapping("/analysisList")
    public void update(@RequestBody AnalysisDirectionDto analysisDirectionDto) {
        analysisDirectionService.changeResultsAnalysisDirection(analysisDirectionDto.id(), analysisDirectionDto.result());
    }
}
