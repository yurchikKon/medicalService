package com.blueTeam.medicalService.config;

import com.blueTeam.medicalService.client.PaymentServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientConfig {

    @Bean
    public PaymentServiceClient paymentServiceClientRest(
            @Value("${client.payment.service.url:http://localhost:8080}") String url
    ) {
        return new PaymentServiceClient(RestClient.builder()
                .baseUrl(url)
                .build());
    }
}
