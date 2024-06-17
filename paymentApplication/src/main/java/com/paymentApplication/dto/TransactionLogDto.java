package com.paymentApplication.dto;

import java.time.LocalDateTime;

public record TransactionLogDto(
        Long id,
        String message,
        LocalDateTime createdAt,
        TransactionDto transactionDto
) {
}
