package com.blueTeam.medicalService.dto.user.doctor.remark;

import com.blueTeam.medicalService.validation.group.UpdateAction;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record DoctorRemarkDto(

        @NotBlank(groups = {UpdateAction.class})
        Long id,

        String remark
) {}
