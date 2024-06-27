package com.blueTeam.medicalService.controller;

import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;
import com.blueTeam.medicalService.dto.security.AuthenticationRequest;
import com.blueTeam.medicalService.dto.security.AuthenticationResponse;
import com.blueTeam.medicalService.entity.enums.DirectionStatus;
import com.blueTeam.medicalService.entity.enums.Usage;
import com.blueTeam.medicalService.service.AnalysisDirectionService;
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
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static com.blueTeam.medicalService.entity.enums.DirectionStatus.INVALID;
import static com.blueTeam.medicalService.entity.enums.Usage.USED;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(printOnlyOnFailure = false)
class AnalysisDirectionControllerIT {
    private static final String ANALYSIS_DIRECTION_DTO = "{\"id\":1,\"status\":\"INVALID\",\"usage\":\"USED\",\"result\":null}";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AnalysisDirectionService analysisDirectionService;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @Transactional
    public void passAnalysis() throws Exception{
        ResponseEntity<String> token = restTemplate.exchange("/api/v1/security/authentication",
                HttpMethod.POST,
                new HttpEntity<>(new AuthenticationRequest("yura@", "Yura123")),
                String.class);

        RequestBuilder requestBuilder = put("/api/v1/analysis-directions/1/usage")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token.getBody().substring(10,token.getBody().length()-2));

        mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isOk(),
                        content().contentType(APPLICATION_JSON),
                        content().json(ANALYSIS_DIRECTION_DTO)
                );
    }

}