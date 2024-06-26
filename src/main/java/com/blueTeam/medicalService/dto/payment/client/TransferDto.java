package com.blueTeam.medicalService.dto.payment.client;

import java.math.BigDecimal;

public record TransferDto(
        PaymentMethodDto paymentMethod,
        Currency currency,
        BigDecimal amount,
        Long receiverId,
        Long senderId
) {
}
