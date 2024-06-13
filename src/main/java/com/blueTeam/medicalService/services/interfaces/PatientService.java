package com.blueTeam.medicalService.services.interfaces;

import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;

import java.util.List;

public interface PatientService {
    List<DoctorAppointmentRepresentationDto> getActivePatientAppointmentDto(Long id);

   }
