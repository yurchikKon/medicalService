package com.blueTeam.medicalService.dto.user.doctor.remark;

import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;
import lombok.Builder;

@Builder
public record DoctorRemarkRepresentationDto (
        Long id,
        String remark,
        DoctorAppointmentRepresentationDto doctorAppointmentDto
) {}
