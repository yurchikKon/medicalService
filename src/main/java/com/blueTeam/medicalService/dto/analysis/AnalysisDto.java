package com.blueTeam.medicalService.dto.analysis;

import com.blueTeam.medicalService.validation.group.CreateAction;
import com.blueTeam.medicalService.validation.group.UpdateAction;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AnalysisDto(

        @NotBlank(groups = {UpdateAction.class})
        Long id,

        @NotBlank(groups = {CreateAction.class})
        @Size(min = 3, max = 255)
        String name,

        @DecimalMin(value = "0.0")
        BigDecimal cost
) {
}
