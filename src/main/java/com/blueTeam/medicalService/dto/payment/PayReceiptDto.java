package com.blueTeam.medicalService.dto.payment;

import com.blueTeam.medicalService.entity.enums.PaymentMethod;
import com.blueTeam.medicalService.entity.enums.PaymentType;
import com.blueTeam.medicalService.entity.enums.ReceiptStatus;
import com.blueTeam.medicalService.validation.group.UpdateAction;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class PayReceiptDto {

    @NotBlank(groups = {UpdateAction.class})
    Long id;

    @DecimalMin(value = "0.0")
    BigDecimal value;

    ReceiptStatus status;

    PaymentMethod paymentMethod;

    PaymentType paymentType;
}
