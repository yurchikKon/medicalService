package com.blueTeam.medicalService.controller;

import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;
import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionNamedDto;
import com.blueTeam.medicalService.service.interfaces.AnalysisDirectionService;
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

    @GetMapping("/patient/{id}")
    public List<AnalysisDirectionNamedDto> getActiveAnalysisForPatient(@PathVariable Long id) {
        return analysisDirectionService.getActiveTestAppointments(id);
    }
}
