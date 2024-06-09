package com.blueTeam.medicalService.dto.user.doctor.appointment;

import com.blueTeam.medicalService.dto.user.UserCreateEditDto;
import com.blueTeam.medicalService.dto.user.doctor.DoctorCreateEditDto;
import com.blueTeam.medicalService.dto.user.patient.PatientCreateEditDto;
import com.blueTeam.medicalService.entities.enums.Status;
import com.blueTeam.medicalService.validation.group.UpdateAction;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record DoctorAppointmentCreateEditDto(

        @NotBlank(groups = {UpdateAction.class})
        Long id,

        DoctorCreateEditDto doctorDto,
        PatientCreateEditDto patientDto,

        @NotBlank(groups = {UserCreateEditDto.class, UpdateAction.class})
        LocalDateTime dateTime,

        @NotBlank(groups = {UserCreateEditDto.class, UpdateAction.class})
        Status status) {

}
