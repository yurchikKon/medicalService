package com.blueTeam.medicalService.dto.diagnosis;

import com.blueTeam.medicalService.validation.group.UpdateAction;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PatientDiagnosisDto {

    @NotBlank(groups = {UpdateAction.class})
    Long id;

}
