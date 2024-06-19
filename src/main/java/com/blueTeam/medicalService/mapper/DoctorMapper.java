package com.blueTeam.medicalService.mapper;

import com.blueTeam.medicalService.dto.user.UserCreateEditDto;
import com.blueTeam.medicalService.dto.user.doctor.DoctorRepresentationDto;
import com.blueTeam.medicalService.entity.Doctor;
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
public abstract class DoctorMapper {

    @Autowired
    protected PasswordEncoderUtil passwordEncoder;

    public abstract DoctorRepresentationDto mapToDto(Doctor doctor);

    public abstract Doctor mapToEntity(DoctorRepresentationDto dto);

    @Mapping(target = "password", expression = "java(passwordEncoder.encode(dto.getPassword()))")
    public abstract Doctor mapToEntity(UserCreateEditDto dto);
}
