package com.blueTeam.medicalService.dto.diagnosis;

import com.blueTeam.medicalService.validation.group.CreateAction;
import com.blueTeam.medicalService.validation.group.UpdateAction;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DiagnosisDto {

    @NotBlank(groups = {UpdateAction.class})
    Long id;

    @NotBlank(groups = {CreateAction.class, UpdateAction.class})
    @Size(
            min = 3, max = 255,
            groups = {CreateAction.class, UpdateAction.class}
    )
    String name;
}
