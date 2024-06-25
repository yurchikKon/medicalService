package com.blueTeam.medicalService.mapper;

import com.blueTeam.medicalService.dto.payment.PayReceiptDto;
import com.blueTeam.medicalService.entity.PayReceipt;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Component
@Mapper(componentModel = SPRING)
public interface PayReceiptMapper {

    PayReceiptDto mapToDto(PayReceipt entity);

    PayReceipt mapToEntity(PayReceiptDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    PayReceipt update(PayReceiptDto dto, @MappingTarget PayReceipt entity);
}
