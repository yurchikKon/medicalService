package com.paymentApplication.dto;

import java.time.LocalDateTime;

public record UserDto(
        Long id,
        String name,
        String email,
        LocalDateTime createdAt
) {
}
