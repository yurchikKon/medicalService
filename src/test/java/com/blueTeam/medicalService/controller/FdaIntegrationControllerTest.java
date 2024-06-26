package com.blueTeam.medicalService.controller;

import com.blueTeam.medicalService.dto.fda.DrugDto;
import com.blueTeam.medicalService.service.DrugService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.MockitoCore;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FdaIntegrationControllerTest {
    private static final DrugDto ASPIRIN_DTO = createDrugDto("aspirin");
    private static final String ASPIRIN_NAME = "aspirin";

    @InjectMocks
    FdaIntegrationController fdaIntegrationController;

    @Mock
    DrugService drugService;

    @Test
    void getDrugInfo() {
        when(drugService.getDrugInfo(ASPIRIN_NAME)).thenReturn(ASPIRIN_DTO);

        assertEquals(ASPIRIN_DTO, fdaIntegrationController.getDrugInfo(ASPIRIN_NAME));
    }

    private static DrugDto createDrugDto(String name) {
        return DrugDto.builder()
                .name(name)
                .build();
    }
}