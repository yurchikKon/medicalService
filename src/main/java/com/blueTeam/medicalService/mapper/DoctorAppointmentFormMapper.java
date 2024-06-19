package com.blueTeam.medicalService.mapper;

import com.blueTeam.medicalService.dto.user.DoctorAppointmentFormDto;
import com.blueTeam.medicalService.entity.DoctorAppointment;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Component
@Mapper(
        componentModel = SPRING,
        uses = {
                DoctorMapper.class,
                PatientMapper.class,
                MedicalProcedureMapper.class,
                DoctorRemarkMapper.class,
                AnalysisDirectionMapper.class,
                MedicalReceiptMapper.class,
                AppointmentReviewMapper.class,
                DiagnosisMapper.class,
                PayReceiptMapper.class
        }
)
public interface DoctorAppointmentFormMapper {

        DoctorAppointmentFormDto mapToDto(DoctorAppointment entity);

        DoctorAppointment mapToEntity(DoctorAppointmentFormDto dto);

        @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
        DoctorAppointment update(DoctorAppointmentFormDto dto, @MappingTarget DoctorAppointment entity);
}
