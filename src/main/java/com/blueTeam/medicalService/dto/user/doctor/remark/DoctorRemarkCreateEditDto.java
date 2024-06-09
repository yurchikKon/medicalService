package com.blueTeam.medicalService.dto.user.doctor.remark;

import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentCreateEditDto;
import com.blueTeam.medicalService.validation.group.UpdateAction;
import jakarta.validation.constraints.NotBlank;

public record DoctorRemarkCreateEditDto (

        @NotBlank(groups = {UpdateAction.class})
        Long id,

        String remark,

        DoctorAppointmentCreateEditDto doctorAppointmentDto
) {}
