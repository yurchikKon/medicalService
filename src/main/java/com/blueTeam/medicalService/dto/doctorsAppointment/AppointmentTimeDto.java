package com.blueTeam.medicalService.dto.doctorsAppointment;

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
