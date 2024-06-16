package com.blueTeam.medicalService.mapper;

import com.blueTeam.medicalService.dto.medicalRecipient.MedicalProcedureDto;
import com.blueTeam.medicalService.entities.MedicalProcedure;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Component
@Mapper(componentModel = SPRING)
public interface MedicalProcedureMapper {

    MedicalProcedureDto mapToDto(MedicalProcedure entity);

    MedicalProcedure mapToEntity(MedicalProcedureDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    MedicalProcedure update(MedicalProcedureDto dto, @MappingTarget MedicalProcedure entity);
}
