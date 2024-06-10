package com.blueTeam.medicalService.dto.user.doctor.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentTimeDto {

    private LocalTime timeStart;
}
