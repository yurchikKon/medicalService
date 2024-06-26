package com.blueTeam.medicalService.service;


import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;
import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;

import java.util.List;

public interface PatientService {
    List<AnalysisDirectionDto> findActivePatientAnalysisDirections(Long patientId);
    List<DoctorAppointmentRepresentationDto> findActivePatientAppointments(Long patientId);
    boolean isPatientPresent(Long id);
}
