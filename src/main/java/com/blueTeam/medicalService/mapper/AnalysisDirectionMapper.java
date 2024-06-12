package com.blueTeam.medicalService.mapper;

import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;
import com.blueTeam.medicalService.entities.AnalysisDirection;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Component
@Mapper(componentModel = SPRING)
public interface AnalysisDirectionMapper {

    AnalysisDirection mapToEntity(AnalysisDirectionDto dto);

    AnalysisDirectionDto mapToDto(AnalysisDirection entity);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    AnalysisDirection update(AnalysisDirectionDto dto, @MappingTarget AnalysisDirection entity);
}
