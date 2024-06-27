package com.blueTeam.medicalService.dto.payment.client;

public record PaymentMethodDto(
        Long id,
        String details,
        PaymentType paymentType) {
}
