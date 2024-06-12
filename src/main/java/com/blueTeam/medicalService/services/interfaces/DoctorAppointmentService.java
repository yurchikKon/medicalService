package com.blueTeam.medicalService.services.interfaces;


import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;
import com.blueTeam.medicalService.entities.DoctorAppointment;

import java.time.LocalDateTime;
import java.util.List;

public interface DoctorAppointmentService {
    List<DoctorAppointmentRepresentationDto> getDoctorAppointmentRepresentationDto(LocalDateTime dateTime);
    List<DoctorAppointment> getDoctorAppointmentByDateTime(LocalDateTime dateTime);
    List<DoctorAppointmentRepresentationDto> convertAppointmentListToDto(List<DoctorAppointment> appointments);
}
