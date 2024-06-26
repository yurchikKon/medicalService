package com.blueTeam.medicalService.service.implementation;

import com.blueTeam.medicalService.dto.fda.DrugDto;
import com.blueTeam.medicalService.feign.FdaServiceClient;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.JsonNode;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Disabled
class DrugServiceImplTest {
    private static final String ASPIRIN_NAME = "aspirin";
    private static final DrugDto DRUG_DTO = createDrugDto("aspirin", "Low Dose", "Do not rely",
            "Warnings", "Active", "Purpose");
    private static final String JSON = "{\n" +
            "    \"meta\": {\n" +
            "        \"disclaimer\": \"Do not rely\"\n" +
            "        }\n" +
            "    },\n" +
            "    \"results\": [\n" +
            "        {\n" +
            "            \"spl_product_data_elements\": [\n" +
            "                \"Low Dose\"\n" +
            "            ],\n" +
            "            \"active_ingredient\": [\n" +
            "                \"Active\"\n" +
            "            ],\n" +
            "            \"purpose\": [\n" +
            "                \"Purpose\"\n" +
            "            ],\n" +
            "            \"warnings\": [\n" +
            "                \"Warnings\"\n" +
            "            ]\n" +
            "            }\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    @InjectMocks
    DrugServiceImpl drugService;

    @Mock
    FdaServiceClient fdaServiceClient;

    @Test
    void getDrugInfo() throws IOException {
        when(fdaServiceClient.getDrugInfo(ASPIRIN_NAME)).thenReturn(JSON);

        assertEquals(DRUG_DTO, drugService.getDrugInfo(ASPIRIN_NAME));
    }

    private static DrugDto createDrugDto(String name, String elements, String disclaimer,
                                         String warnings, String ingredients, String purpose) {
        return DrugDto.builder()
                .productElements(elements)
                .activeIngredient(ingredients)
                .disclaimer(disclaimer)
                .purpose(purpose)
                .warnings(warnings)
                .name(name)
                .build();
    }
}