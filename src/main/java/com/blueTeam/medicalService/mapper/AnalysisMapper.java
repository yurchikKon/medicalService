package com.blueTeam.medicalService.mapper;

import com.blueTeam.medicalService.dto.analysis.AnalysisDto;
import com.blueTeam.medicalService.entities.Analysis;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Component
@Mapper(componentModel = SPRING)
public interface AnalysisMapper {


    @Mapping(target = "analysisDirection", ignore = true)
    Analysis mapToEntity(AnalysisDto dto);

    AnalysisDto mapToDto(Analysis entity);

    @Mappings({
            @Mapping(target = "analysisDirection", ignore = true),
            @Mapping(target = "name", conditionExpression = "java(!dto.name().isEmpty())")
    })
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Analysis update(AnalysisDto dto, @MappingTarget Analysis entity);
}
