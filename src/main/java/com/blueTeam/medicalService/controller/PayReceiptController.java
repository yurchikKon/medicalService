package com.blueTeam.medicalService.controller;

import com.blueTeam.medicalService.dto.payment.PayReceiptDto;
import com.blueTeam.medicalService.service.interfaces.PayReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/payReceipts")
public class PayReceiptController {
    private final PayReceiptService payReceiptService;

    @PostMapping("/analysis/{analysisDirectionId}")
    public PayReceiptDto createAnalysisDirectionPayReceipt(@PathVariable Long analysisDirectionId) {
        return payReceiptService.createAnalysisPayReceipt(analysisDirectionId);
    }

    @PostMapping("/appoiment/{appointmentId}")
    public PayReceiptDto createDoctorAppointmentPayReceipt(@PathVariable Long appointmentId) {
        return payReceiptService.createDoctorAppointmentPayReceipt(appointmentId);
    }

}
