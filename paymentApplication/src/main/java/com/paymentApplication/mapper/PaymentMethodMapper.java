package com.paymentApplication.mapper;

import com.paymentApplication.dto.PaymentMethodDto;
import com.paymentApplication.model.entity.PaymentMethod;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Component
@Mapper(componentModel = SPRING)
public interface PaymentMethodMapper {

    PaymentMethod mapToEntity(PaymentMethodDto dto);

    PaymentMethodDto mapToDto(PaymentMethod method);
}
