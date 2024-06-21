package com.blueTeam.medicalService.controller;

import com.blueTeam.medicalService.dto.fda.DrugDto;
import com.blueTeam.medicalService.service.DrugService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fda")
@RequiredArgsConstructor
public class FdaIntegrationController {
    private final DrugService drugService;

    @GetMapping("/{name}")
    public DrugDto getDrugInfo(@PathVariable String name) {
      return drugService.getDrugInfo(name);
    }

}
