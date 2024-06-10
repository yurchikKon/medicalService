package com.blueTeam.medicalService.services.interfaces;

import com.blueTeam.medicalService.dto.user.patient.PatientActiveAppointmentDto;
import com.blueTeam.medicalService.entities.DoctorAppointment;

import java.util.List;

public interface PatientService {
    List<DoctorAppointment> getActiveDoctorAppointmentByUserId(Long id);
    List<PatientActiveAppointmentDto> convertAppointmentListToDto(List<DoctorAppointment> appointments);
    List<PatientActiveAppointmentDto> getActivePatientAppointmentDto(Long id);
}
