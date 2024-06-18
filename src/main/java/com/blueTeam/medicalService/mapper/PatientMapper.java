package com.blueTeam.medicalService.mapper;

import com.blueTeam.medicalService.dto.user.patient.PatientCreateEditDto;
import com.blueTeam.medicalService.dto.user.patient.PatientRepresentationDto;
import com.blueTeam.medicalService.entity.Patient;
import com.blueTeam.medicalService.mapper.util.PasswordEncoderUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Component
@Mapper(
        componentModel = SPRING,
        imports = {Optional.class, StringUtils.class}
)
public abstract class PatientMapper {

    @Autowired
    protected PasswordEncoderUtil passwordEncoder;

    public abstract PatientRepresentationDto mapToDto(Patient patient);

    public abstract Patient mapToEntity(PatientRepresentationDto dto);

    @Mapping(target = "password", expression = "java(passwordEncoder.encode(dto.getPassword()))")
    public abstract Patient mapToEntity(PatientCreateEditDto dto);
}
