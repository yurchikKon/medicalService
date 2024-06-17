package com.blueTeam.medicalService.controllers;

import com.blueTeam.medicalService.dto.payment.PayReceiptDto;
import com.blueTeam.medicalService.services.interfaces.PayReceiptService;
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

    @GetMapping("/{patientId}/payReceipts")
    public List<PayReceiptDto> findAllPayReceiptsByPatientId(@PathVariable Long patientId) {
        return payReceiptService.findAllByPatientId(patientId);
    }
}
