package com.blueTeam.medicalService.dto.user.patient;

import com.blueTeam.medicalService.dto.user.UserCreateEditDto;
import com.blueTeam.medicalService.entity.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Data
@SuperBuilder
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class PatientCreateEditDto extends UserCreateEditDto {

    Gender gender;

    LocalDate birthDate;
}
