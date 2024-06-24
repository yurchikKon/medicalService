package com.blueTeam.medicalService.controller;

import com.blueTeam.medicalService.dto.payment.PayReceiptDto;
import com.blueTeam.medicalService.entity.enums.PaymentType;
import com.blueTeam.medicalService.service.PayReceiptService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.parameters.P;

import java.util.List;

import static com.blueTeam.medicalService.entity.enums.PaymentType.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientControllerTest {
    private static final PayReceiptDto PAY_RECEIPT_ANALYSIS_DTO = createPayReceiptDto(1L, ANALYSIS);
    private static final PayReceiptDto PAY_RECEIPT_SERVICE_DTO = createPayReceiptDto(2L, SERVICE);
    private static final Long PATIENT_ID = 1L;

    @InjectMocks
    PatientController patientController;

    @Mock
    PayReceiptService payReceiptService;

    @Test
    void findAllPayReceiptsByPatientId() {
        when(payReceiptService.findAllByPatientId(PATIENT_ID))
                .thenReturn(List.of(PAY_RECEIPT_SERVICE_DTO, PAY_RECEIPT_ANALYSIS_DTO));

        List<PayReceiptDto> expected = List.of(PAY_RECEIPT_SERVICE_DTO, PAY_RECEIPT_ANALYSIS_DTO);

        assertEquals(expected, patientController.findAllPayReceiptsByPatientId(PATIENT_ID));
    }

    private static PayReceiptDto createPayReceiptDto(Long id, PaymentType type) {
        return PayReceiptDto.builder()
                .id(id)
                .paymentType(type)
                .build();
    }
}