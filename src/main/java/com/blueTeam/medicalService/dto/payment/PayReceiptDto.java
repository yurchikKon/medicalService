package com.blueTeam.medicalService.dto.payment;

import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;
import com.blueTeam.medicalService.entities.enums.PaymentMethod;
import com.blueTeam.medicalService.entities.enums.PaymentType;
import com.blueTeam.medicalService.entities.enums.ReceiptStatus;
import com.blueTeam.medicalService.validation.group.UpdateAction;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class PayReceiptDto {

    @NotBlank(groups = {UpdateAction.class})
    Long id;

    DoctorAppointmentRepresentationDto doctorAppointmentDto;

    BigDecimal value;

    ReceiptStatus status;

    PaymentMethod paymentMethod;

    PaymentType paymentType;
}
