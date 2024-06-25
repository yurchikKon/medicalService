package com.blueTeam.medicalService.controller;

import com.blueTeam.medicalService.dto.payment.PayReceiptDto;
import com.blueTeam.medicalService.service.PayReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/payReceipts")
public class PayReceiptController {
    private final PayReceiptService payReceiptService;

    @PostMapping("/analysis/{analysisDirectionId}")
    public PayReceiptDto createAnalysisDirectionPayReceipt(@PathVariable Long analysisDirectionId) {
        return payReceiptService.createAnalysisPayReceipt(analysisDirectionId);
    }

}
