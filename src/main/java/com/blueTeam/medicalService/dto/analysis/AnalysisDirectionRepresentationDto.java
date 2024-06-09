package com.blueTeam.medicalService.dto.analysis;

import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;
import com.blueTeam.medicalService.entities.enums.DirectionStatus;
import com.blueTeam.medicalService.entities.enums.Usage;


public record AnalysisDirectionRepresentationDto(
        Long id,
        DoctorAppointmentRepresentationDto doctorsAppointmentDto,
        AnalysisRepresentationDto analysisDto,
        DirectionStatus status,
        Usage usage,
        String result) {

}
