package com.blueTeam.medicalService.dto.user.patient;

import com.blueTeam.medicalService.dto.user.UserRepresentationDto;
import com.blueTeam.medicalService.entities.DoctorAppointment;
import com.blueTeam.medicalService.entities.enums.Gender;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;


@Data
@Builder
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class PatientRepresentationDto extends UserRepresentationDto {
    Gender gender;
    LocalDate birthDate;
    List<DoctorAppointment> doctorsAppointments;
}
