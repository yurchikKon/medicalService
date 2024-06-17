package com.paymentApplication.dto;

import java.time.LocalDateTime;

public record PaymentMethodDto(
        Long id,
        String paymentType,
        String details,
        LocalDateTime createdAt
) {
}
