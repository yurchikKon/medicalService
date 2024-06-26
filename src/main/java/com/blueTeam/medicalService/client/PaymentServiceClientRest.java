package com.blueTeam.medicalService.client;

import com.blueTeam.medicalService.dto.payment.client.TransferDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class PaymentServiceClientRest {

    private final RestClient restClient;

    public String pay(TransferDto transferDto) {
        return restClient.get()
                .uri("/api/v1/payment")
                .retrieve()
                .body(String.class);
    }
}
