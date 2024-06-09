package com.blueTeam.medicalService.dto.user.doctor.specialization;

import com.blueTeam.medicalService.validation.group.CreateAction;
import com.blueTeam.medicalService.validation.group.UpdateAction;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record SpecializationDto(

        @NotBlank(groups = {UpdateAction.class})
        Long id,

        @NotBlank(groups = {CreateAction.class})
        @Size(min = 1, max = 255, groups = {CreateAction.class, UpdateAction.class})
        String name
) {}
