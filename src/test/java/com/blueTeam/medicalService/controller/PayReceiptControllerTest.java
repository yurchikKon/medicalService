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

import static com.blueTeam.medicalService.entity.enums.PaymentType.ANALYSIS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PayReceiptControllerTest {
    private static final PayReceiptDto PAY_RECEIPT_ANALYSIS_DTO = createPayReceiptDto(1L, ANALYSIS);
    private static final Long ANALYSIS_DIRECTION_ID = 1L;

    @InjectMocks
    PayReceiptController payReceiptController;

    @Mock
    PayReceiptService payReceiptService;

    @Test
    void createAnalysisDirectionPayReceipt() {
        when(payReceiptService.createAnalysisPayReceipt(ANALYSIS_DIRECTION_ID)).thenReturn(PAY_RECEIPT_ANALYSIS_DTO);

        assertEquals(PAY_RECEIPT_ANALYSIS_DTO, payReceiptController.createAnalysisDirectionPayReceipt(ANALYSIS_DIRECTION_ID));
    }

    private static PayReceiptDto createPayReceiptDto(Long id, PaymentType type) {
        return PayReceiptDto.builder()
                .id(id)
                .paymentType(type)
                .build();
    }
}