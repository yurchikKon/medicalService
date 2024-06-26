package com.blueTeam.medicalService.mapper;

import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionNamedDto;
import com.blueTeam.medicalService.entity.Analysis;
import com.blueTeam.medicalService.entity.AnalysisDirection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Component
@Mapper(componentModel = SPRING)
public interface AnalysisDirectionNamedMapper {

    @Mapping(expression = "java(direction.getId())", target = "id")
    @Mapping(source = "analysis.name", target = "name")
    AnalysisDirectionNamedDto mapToNamedDto(AnalysisDirection direction, Analysis analysis);

}
