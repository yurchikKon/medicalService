package com.blueTeam.medicalService.mapper;

import com.blueTeam.medicalService.dto.user.doctor.remark.DoctorRemarkDto;
import com.blueTeam.medicalService.entities.DoctorRemark;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Component
@Mapper(componentModel = SPRING)
public interface DoctorRemarkMapper {

    DoctorRemarkDto mapToDto(DoctorRemark entity);

    DoctorRemark mapToEntity(DoctorRemarkDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    DoctorRemark update(DoctorRemarkDto dto, @MappingTarget DoctorRemark entity);
}
