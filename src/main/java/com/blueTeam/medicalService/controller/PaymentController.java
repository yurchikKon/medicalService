package com.blueTeam.medicalService.controller;

import com.blueTeam.medicalService.client.PaymentServiceClient;
import com.blueTeam.medicalService.dto.payment.client.TransferDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment")
public class PaymentController {

    private final PaymentServiceClient paymentServiceClient;

    @PostMapping
    public ResponseEntity<TransferDto> pay(@RequestBody TransferDto transferDto) {
        return ResponseEntity.ok()
                .body(paymentServiceClient.pay(transferDto));
    }
}
