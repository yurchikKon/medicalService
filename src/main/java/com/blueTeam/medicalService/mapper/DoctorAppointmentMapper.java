package com.blueTeam.medicalService.mapper;

import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentCreateEditDto;
import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;
import com.blueTeam.medicalService.entity.DoctorAppointment;
import org.mapstruct.*;
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
                PayReceiptMapper.class
        }
)
public interface DoctorAppointmentMapper {

        @Mappings({
                @Mapping(source = "doctor", target = "doctorDto"),
                @Mapping(source = "patient", target = "patientDto"),
                @Mapping(source = "medicalServices", target = "medicalRecipientDtos"),
                @Mapping(source = "directions", target = "specialDoctorDirectionDtos"),
                @Mapping(source = "doctorsRemark", target = "doctorRemarkDto"),
                @Mapping(source = "analysisDirections", target = "analysisDirectionDtos"),
                @Mapping(source = "medicalReceipts", target = "medicalReceiptDtos"),
                @Mapping(source = "appointmentReview", target = "appointmentReviewDto"),
                @Mapping(source = "payReceipts", target = "payReceiptDtos")
        })
        DoctorAppointmentRepresentationDto mapToDto(DoctorAppointment entity);

        @Mappings({
                @Mapping(source = "doctorDto", target = "doctor"),
                @Mapping(source = "patientDto", target = "patient"),
                @Mapping(source = "medicalRecipientDtos", target = "medicalServices"),
                @Mapping(source = "specialDoctorDirectionDtos", target = "directions"),
                @Mapping(source = "doctorRemarkDto", target = "doctorsRemark"),
                @Mapping(source = "analysisDirectionDtos", target = "analysisDirections"),
                @Mapping(source = "medicalReceiptDtos", target = "medicalReceipts"),
                @Mapping(source = "appointmentReviewDto", target = "appointmentReview"),
                @Mapping(source = "payReceiptDtos", target = "payReceipts")
        })
        DoctorAppointment mapToEntity(DoctorAppointmentRepresentationDto dto);

        DoctorAppointment mapToEntity(DoctorAppointmentCreateEditDto dto);

        @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
        DoctorAppointment update(DoctorAppointmentCreateEditDto dto,
                                 @MappingTarget DoctorAppointment entity);
}
