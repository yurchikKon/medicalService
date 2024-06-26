package com.blueTeam.medicalService.config;

import com.blueTeam.medicalService.client.PaymentServiceClientRest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientConfig {

    @Bean
    public PaymentServiceClientRest paymentServiceClientRest(
            @Value("${client.payment.service.url:http://localhost:8080}") String url
    ) {
        return new PaymentServiceClientRest(RestClient.builder()
                .baseUrl(url)
                .build());
    }
}
