package com.blueTeam.medicalService.dto.user.doctor;

import com.blueTeam.medicalService.dto.user.UserRepresentationDto;
import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;
import com.blueTeam.medicalService.dto.user.doctor.specialization.SpecializationDto;
import com.blueTeam.medicalService.dto.user.doctor.timetable.DoctorTimetableDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@SuperBuilder
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class DoctorRepresentationDto extends UserRepresentationDto {
        SpecializationDto specializationDto;
        Double rate;
        List<DoctorTimetableDto>timetableDtos;
        List<DoctorAppointmentRepresentationDto> doctorAppointmentDtos;
}
