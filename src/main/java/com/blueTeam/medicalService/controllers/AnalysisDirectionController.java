package com.blueTeam.medicalService.controllers;

import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;
import com.blueTeam.medicalService.services.interfaces.AnalysisDirectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/analysisDirections")
public class AnalysisDirectionController {
    private final AnalysisDirectionService analysisDirectionService;

    @GetMapping("/patients/{id}")
    public ResponseEntity<List<AnalysisDirectionDto>> getAnalysisDirection(@PathVariable("id") Long id) {
        return ResponseEntity.ok(analysisDirectionService.findAllByPatientidAndUsage(id));
    }
}
