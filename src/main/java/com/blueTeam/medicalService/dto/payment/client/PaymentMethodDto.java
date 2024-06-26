package com.blueTeam.medicalService.dto.payment.client;

import java.time.LocalDateTime;

public record PaymentMethodDto(
        Long id,
        String details,
        LocalDateTime createdAt,
        PaymentType paymentType) {
}
