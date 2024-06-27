package com.paymentApplication.dto;

import com.paymentApplication.model.entity.PaymentType;

public record PaymentMethodDto(
        Long id,
        PaymentType paymentType,
        String details
) {
}
