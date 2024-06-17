package com.blueTeam.medicalService.services.interfaces;


import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;

import java.time.LocalDate;
import java.util.List;

public interface DoctorAppointmentService {
    List<DoctorAppointmentRepresentationDto> getAllDoctorsAppointmentRepresentationDto(LocalDate localdate);

}
