package com.blueTeam.medicalService.dto.analysis;

import com.blueTeam.medicalService.entity.enums.DirectionStatus;
import com.blueTeam.medicalService.entity.enums.Usage;
import com.blueTeam.medicalService.validation.group.CreateAction;
import com.blueTeam.medicalService.validation.group.UpdateAction;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record AnalysisDirectionDto(

        @NotBlank(groups = {UpdateAction.class})
        Long id,

        DirectionStatus status,

        Usage usage,

        @Size(
                max = 255,
                groups = {CreateAction.class, UpdateAction.class}
        )
        String result) {
}
