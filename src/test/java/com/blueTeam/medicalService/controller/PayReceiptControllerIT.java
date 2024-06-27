package com.blueTeam.medicalService.controller;

import com.blueTeam.medicalService.dto.security.AuthenticationRequest;
import com.blueTeam.medicalService.service.PayReceiptService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PayReceiptControllerIT {
    private final static String PAY_RECEIPT_DTO = "{\"id\":6,\"value\":1500.00,\"status\":\"PENDING\"" +
            ",\"paymentMethod\":\"CARD\",\"paymentType\":\"ANALYSIS\"}";

    @Autowired
    PayReceiptService payReceiptService;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    MockMvc mockMvc;

    @Test
    @Transactional
    public void createAnalysisDirectionPayReceipt() throws Exception {
        ResponseEntity<String> token = restTemplate.exchange("/api/v1/security/authentication",
                HttpMethod.POST,
                new HttpEntity<>(new AuthenticationRequest("yura@", "Yura123")),
                String.class);

        RequestBuilder requestBuilder = post("/api/v1/payReceipts/analysis/2")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token.getBody().substring(10,token.getBody().length()-2));

        mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isOk(),
                        content().contentType(APPLICATION_JSON),
                        content().json(PAY_RECEIPT_DTO)
                );
    }

}