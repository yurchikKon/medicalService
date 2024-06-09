package com.blueTeam.medicalService.dto.receipt;

import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;
import com.blueTeam.medicalService.validation.group.UpdateAction;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class MedicalReceiptDto {

    @NotBlank(groups = {UpdateAction.class})
    Long id;

    LocalDate dateEnd;

    PreparationDto preparationDto;
    DoctorAppointmentRepresentationDto doctorAppointmentDto;
}
