package com.paymentApplication.dto;

import com.paymentApplication.model.entity.Currency;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AccountDto(
        Long id,
        BigDecimal balance,
        LocalDateTime createdAt,
        Currency currency,
        UserDto userDto) {

}
