package com.blueTeam.medicalService.dto.user.doctor.appointment;

import com.blueTeam.medicalService.validation.group.UpdateAction;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class AppointmentReviewDto {

    @NotBlank(groups = {UpdateAction.class})
    Long id;

    Double mark;
}
