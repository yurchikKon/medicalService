package com.paymentApplication.dto;

import java.math.BigDecimal;

public record TransferDto(
        AccountDto accountFrom,
        AccountDto accountTo,
        BigDecimal amount
) {
}
