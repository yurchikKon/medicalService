package com.blueTeam.medicalService.dto.user.patient;

import com.blueTeam.medicalService.dto.user.UserCreateEditDto;
import com.blueTeam.medicalService.entities.enums.Gender;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class PatientCreateEditDto extends UserCreateEditDto {

    Gender gender;

    LocalDate birthDate;
}
