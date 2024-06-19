package com.blueTeam.medicalService.mapper;

import com.blueTeam.medicalService.dto.user.doctor.SpecialDoctorDirectionDto;
import com.blueTeam.medicalService.entity.SpecialDoctorDirection;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Component
@Mapper(componentModel = SPRING)
public interface SpecialDoctorDirectionMapper {

    SpecialDoctorDirectionDto mapToDto(SpecialDoctorDirection entity);

    SpecialDoctorDirection mapToEntity(SpecialDoctorDirectionDto dto);

    SpecialDoctorDirection updateEntity(SpecialDoctorDirectionDto dto, @MappingTarget SpecialDoctorDirection entity);

}
