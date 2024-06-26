package com.blueTeam.medicalService.controller;

import com.blueTeam.medicalService.client.PaymentServiceClientRest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/grpc")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentServiceClientRest paymentServiceClient;

    @GetMapping("/pay")
    public String pay() {
        return paymentServiceClient.pay(null);
    }
}
