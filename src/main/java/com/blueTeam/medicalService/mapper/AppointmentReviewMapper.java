package com.blueTeam.medicalService.mapper;

import com.blueTeam.medicalService.dto.user.doctor.appointment.AppointmentReviewDto;
import com.blueTeam.medicalService.entity.AppointmentReview;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Component
@Mapper(componentModel = SPRING)
public interface AppointmentReviewMapper {

    AppointmentReviewDto mapToDto(AppointmentReview entity);

    AppointmentReview mapToEntity(AppointmentReviewDto dto);

    AppointmentReview update(AppointmentReviewDto dto, @MappingTarget AppointmentReview entity);
}
