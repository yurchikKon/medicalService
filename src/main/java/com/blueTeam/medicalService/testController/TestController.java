package com.blueTeam.medicalService.testController;


import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;
import com.blueTeam.medicalService.entities.AnalysisDirection;
import com.blueTeam.medicalService.services.interfaces.AnalysisDirectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestController {
    private final AnalysisDirectionService analysisDirectionService;

    @GetMapping("/test")
    public String getAnalysisDirection() {
        return "Hello world";
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<AnalysisDirectionDto>> getAnalysisDirection(@PathVariable("id") Long id) {
        return ResponseEntity.ok(analysisDirectionService.findAllByPatientidAndUsage(id));
    }
}
