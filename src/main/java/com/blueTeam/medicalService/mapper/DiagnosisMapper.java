package com.blueTeam.medicalService.mapper;

import com.blueTeam.medicalService.dto.diagnosis.DiagnosisDto;
import com.blueTeam.medicalService.entities.Diagnosis;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Component
@Mapper(componentModel = SPRING)
public interface DiagnosisMapper {

    DiagnosisDto mapToDto(Diagnosis diagnosis);

    Diagnosis mapToEntity(DiagnosisDto dto);

    Diagnosis update(DiagnosisDto dto, @MappingTarget Diagnosis entity);
}
