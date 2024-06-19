package com.blueTeam.medicalService.mapper;

import com.blueTeam.medicalService.dto.diagnosis.PatientDiagnosisDto;
import com.blueTeam.medicalService.entity.PatientDiagnosis;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Component
@Mapper(componentModel = SPRING)
public interface PatientDiagnosisMapper {

    PatientDiagnosis mapToEntity(PatientDiagnosisDto dto);

    PatientDiagnosisDto mapToDto(PatientDiagnosis entity);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    PatientDiagnosis update(PatientDiagnosisDto dto, @MappingTarget PatientDiagnosis entity);

}


