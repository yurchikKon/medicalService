package com.blueTeam.medicalService.mapper;

import com.blueTeam.medicalService.dto.user.doctor.specialization.SpecializationDto;
import com.blueTeam.medicalService.entities.Specialization;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Component
@Mapper(componentModel = SPRING)
public interface SpecializationMapper {

    SpecializationDto mapToDto(Specialization specialization);

    Specialization mapToEntity(SpecializationDto specializationDto);

    Specialization updateEntity(SpecializationDto specializationDto, @MappingTarget Specialization specialization);
}
