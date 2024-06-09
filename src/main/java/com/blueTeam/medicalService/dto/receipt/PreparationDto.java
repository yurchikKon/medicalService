package com.blueTeam.medicalService.dto.receipt;

import com.blueTeam.medicalService.validation.group.CreateAction;
import com.blueTeam.medicalService.validation.group.UpdateAction;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PreparationDto {

    Long id;

    @Size(
            min = 3, max = 255,
            groups = {CreateAction.class, UpdateAction.class}
    )
    String name;
}
