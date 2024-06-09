package com.blueTeam.medicalService.dto.medicalRecipient;

import com.blueTeam.medicalService.validation.group.CreateAction;
import com.blueTeam.medicalService.validation.group.UpdateAction;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record MedicalRecipientCreateEditDto(

        @NotBlank(groups = {UpdateAction.class})
        Long id,

        @NotBlank(groups = {CreateAction.class})
        @Size(
                min = 3, max = 255,
                groups = {CreateAction.class, UpdateAction.class}
        )
        String name,

        BigDecimal cost) {}
