package com.blueTeam.medicalService.client;

import com.blueTeam.medicalService.dto.payment.client.TransferDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RequiredArgsConstructor
public class PaymentServiceClient {

    private final RestClient restClient;

    public TransferDto pay(TransferDto transferDto) {
        return restClient.post()
                .uri("/api/v1/payment")
                .contentType(APPLICATION_JSON)
                .body(transferDto)
                .retrieve()
                .body(TransferDto.class);
    }
}
