package com.blueTeam.medicalService.controller;

import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;
import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionNamedDto;
import com.blueTeam.medicalService.service.AnalysisDirectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/analysisDirections")
public class AnalysisDirectionController {
    private final AnalysisDirectionService analysisDirectionService;

    @PutMapping("/{id}/usage")
    public AnalysisDirectionDto passAnalysis(@PathVariable Long id) {
        return analysisDirectionService.passAnalysis(id);
    }

    @PutMapping("/{id}/result")
    public AnalysisDirectionDto changeAnalysisResult(@PathVariable Long id, @RequestParam String newResult) {
        return analysisDirectionService.changeResultsAnalysisDirection(id, newResult);
    }

    @GetMapping("/patients/{id}/unused")
    public List<AnalysisDirectionNamedDto> getUsedAnalysisForPatient(@PathVariable Long id) {
        return analysisDirectionService.getUsedAnalysisRecords(id);
    }
}
