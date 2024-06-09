package com.blueTeam.medicalService.dto.analysis;

import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentCreateEditDto;
import com.blueTeam.medicalService.entities.enums.DirectionStatus;
import com.blueTeam.medicalService.entities.enums.Usage;
import com.blueTeam.medicalService.validation.group.CreateAction;
import com.blueTeam.medicalService.validation.group.UpdateAction;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AnalysisDirectionCreateEditDto(

        @NotBlank(groups = {CreateAction.class})
        Long id,

        DirectionStatus status,

        Usage usage,

        @Size(
                min = 3, max = 255,
                groups = {CreateAction.class, UpdateAction.class}
        )
        String result,

        AnalysisCreateEditDto analysisDto,

        DoctorAppointmentCreateEditDto doctorsAppointment) {
}
