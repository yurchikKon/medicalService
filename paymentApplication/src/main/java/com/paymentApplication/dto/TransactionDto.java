package com.paymentApplication.dto;

import java.time.LocalDateTime;

public record TransactionDto(
        Long id,
        String currency,
        LocalDateTime createdAt,
        PaymentMethodDto paymentMethodDto,
        String status,
        TransferDto transferDto
) {
}
