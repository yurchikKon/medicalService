package com.blueTeam.medicalService.mapper;

import com.blueTeam.medicalService.dto.user.doctor.timetable.DoctorTimetableDto;
import com.blueTeam.medicalService.entity.DoctorTimetable;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Component
@Mapper(componentModel = SPRING)
public interface DoctorTimetableMapping {

    DoctorTimetableDto mapToDto(DoctorTimetable doctorTimetable);

    DoctorTimetable mapToEntity(DoctorTimetableDto doctorTimetableDto);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    DoctorTimetable update(DoctorTimetableDto dto, @MappingTarget DoctorTimetable entity);
}
