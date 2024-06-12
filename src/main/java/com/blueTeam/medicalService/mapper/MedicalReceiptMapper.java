package com.blueTeam.medicalService.mapper;

import com.blueTeam.medicalService.dto.receipt.MedicalReceiptDto;
import com.blueTeam.medicalService.entities.MedicalReceipt;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Component
@Mapper(componentModel = SPRING)
public interface MedicalReceiptMapper {

    MedicalReceiptDto mapToDto(MedicalReceipt entity);

    MedicalReceipt mapToEntity(MedicalReceiptDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    MedicalReceipt update(MedicalReceiptDto dto, @MappingTarget MedicalReceipt entity);
}
