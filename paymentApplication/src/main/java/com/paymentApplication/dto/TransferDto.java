package com.paymentApplication.dto;

import com.paymentApplication.model.entity.Currency;

import java.math.BigDecimal;

public record TransferDto(
        PaymentMethodDto paymentMethod,
        Currency currency,
        BigDecimal amount,
        Long receiverId,
        Long senderId
) {
}
