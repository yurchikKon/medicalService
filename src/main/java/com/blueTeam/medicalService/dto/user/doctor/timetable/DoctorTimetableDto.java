package com.blueTeam.medicalService.dto.user.doctor.timetable;

import com.blueTeam.medicalService.dto.user.doctor.DoctorRepresentationDto;
import com.blueTeam.medicalService.validation.group.CreateAction;
import com.blueTeam.medicalService.validation.group.UpdateAction;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Builder
public record DoctorTimetableDto(

        @NotBlank(groups = {UpdateAction.class})
        Long id,

        DoctorRepresentationDto doctor,

        @NotBlank(groups = {CreateAction.class, UpdateAction.class})
        DayOfWeek dayOfWeek,

        @NotBlank(groups = {CreateAction.class, UpdateAction.class})
        LocalTime timeStart,

        @NotBlank(groups = {CreateAction.class, UpdateAction.class})
        LocalTime timeEnd,

        @NotBlank(groups = {CreateAction.class, UpdateAction.class})
        Integer roomNumber
) { }
