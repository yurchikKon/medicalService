package com.blueTeam.medicalService.controller;

import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;
import com.blueTeam.medicalService.dto.payment.PayReceiptDto;
import com.blueTeam.medicalService.service.PatientService;
import com.blueTeam.medicalService.service.PayReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/patients")
public class PatientController {
    private final PayReceiptService payReceiptService;
    private final PatientService patientService;

    @GetMapping("/{patientId}/payReceipts")
    public List<PayReceiptDto> findAllPayReceiptsByPatientId(@PathVariable Long patientId) {
        return payReceiptService.findAllByPatientId(patientId);
    }

    @GetMapping("/{patientId}/analysisDirections/valid")
    public List<AnalysisDirectionDto> findActivePatientAnalysisDirections (@PathVariable Long patientId) {
        return patientService.findActivePatientAnalysisDirections(patientId);
    }
}
