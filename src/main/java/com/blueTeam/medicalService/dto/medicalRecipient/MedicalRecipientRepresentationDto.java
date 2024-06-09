package com.blueTeam.medicalService.dto.medicalRecipient;

import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record MedicalRecipientRepresentationDto(
        Long id,
        String name,
        BigDecimal cost,
        List<DoctorAppointmentRepresentationDto> doctorAppointmentDtos
) { }
