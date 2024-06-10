package com.blueTeam.medicalService.dto.user.patient;

import com.blueTeam.medicalService.dto.user.UserRepresentationDto;
import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;
import com.blueTeam.medicalService.entities.enums.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;


@Data
@SuperBuilder
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class PatientRepresentationDto extends UserRepresentationDto {
    Gender gender;
    LocalDate birthDate;
    List<DoctorAppointmentRepresentationDto> doctorAppointmentDtos;
}
