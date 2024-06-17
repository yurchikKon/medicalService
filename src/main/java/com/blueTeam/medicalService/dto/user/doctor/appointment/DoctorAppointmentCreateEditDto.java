package com.blueTeam.medicalService.dto.user.doctor.appointment;

import com.blueTeam.medicalService.dto.user.UserCreateEditDto;
import com.blueTeam.medicalService.dto.user.doctor.DoctorRepresentationDto;
import com.blueTeam.medicalService.dto.user.patient.PatientRepresentationDto;
import com.blueTeam.medicalService.entity.enums.Status;
import com.blueTeam.medicalService.validation.group.UpdateAction;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record DoctorAppointmentCreateEditDto(

        @NotBlank(groups = {UpdateAction.class})
        Long id,

        DoctorRepresentationDto doctorDto,
        PatientRepresentationDto patientDto,

        @NotBlank(groups = {UserCreateEditDto.class, UpdateAction.class})
        LocalDateTime dateTime,

        @NotBlank(groups = {UserCreateEditDto.class, UpdateAction.class})
        Status status) {

}
