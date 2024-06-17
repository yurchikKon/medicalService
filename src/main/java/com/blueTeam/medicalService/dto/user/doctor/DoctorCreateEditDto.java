package com.blueTeam.medicalService.dto.user.doctor;

import com.blueTeam.medicalService.dto.user.UserCreateEditDto;
import com.blueTeam.medicalService.dto.user.doctor.specialization.SpecializationDto;
import com.blueTeam.medicalService.validation.group.CreateAction;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import static lombok.AccessLevel.PRIVATE;

@Data
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = PRIVATE)
public class DoctorCreateEditDto extends UserCreateEditDto {

    @NotBlank(groups = {CreateAction.class})
    SpecializationDto specializationDto;

    Double rate;
}
