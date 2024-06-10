package com.blueTeam.medicalService.dto.user.patient;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@FieldDefaults(level = PRIVATE)
public class PatientActiveAppointmentDto {
    String firstName;
    String lastName;
    String specialization;
    LocalDateTime dateTime;
    Integer roomNumber;
}
