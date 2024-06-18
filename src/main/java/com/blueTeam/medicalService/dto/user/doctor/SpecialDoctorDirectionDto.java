package com.blueTeam.medicalService.dto.user.doctor;

import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;
import com.blueTeam.medicalService.dto.user.doctor.specialization.SpecializationDto;
import lombok.Value;

@Value
public class SpecialDoctorDirectionDto {

    Long id;
    DoctorAppointmentRepresentationDto doctorsAppointment;
    SpecializationDto specialization;
}
