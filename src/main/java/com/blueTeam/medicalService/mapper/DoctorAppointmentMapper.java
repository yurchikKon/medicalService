package com.blueTeam.medicalService.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Component
@Mapper(
        componentModel = SPRING,
        uses = {
                // TODO: add mappers for doctor and patient
                MedicalProcedureMapper.class,
                DoctorRemarkMapper.class,
                AnalysisDirectionMapper.class,
                MedicalReceiptMapper.class,
                PayReceiptMapper.class
        }
)
public interface DoctorAppointmentMapper {

}
