package com.blueTeam.medicalService.test;

import com.blueTeam.medicalService.entities.AnalysisDirection;
import com.blueTeam.medicalService.services.interfaces.AnalysisDirectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TestController {
    private final AnalysisDirectionService analysisDirectionService;
    @GetMapping("/test")
    public String test() {
        return " ok ";
    }
    @GetMapping("/update")
    public void update() {
        analysisDirectionService.changeResultsAnalysisDirection(2l,"Hello world - ok");
    }
}
