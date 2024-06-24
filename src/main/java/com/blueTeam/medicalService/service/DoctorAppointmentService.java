package com.blueTeam.medicalService.service;

import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface DoctorAppointmentService {

    List<DoctorAppointmentRepresentationDto> findAllScheduledByDoctorIdAndDate(Long id, LocalDate localDate);

    List<DoctorAppointmentRepresentationDto> findAllFreeAppointmentsByDoctorIdAndDate(Long id, LocalDate localDate);

    DoctorAppointmentRepresentationDto createAppointment(Long patientId, Long doctorId, LocalDateTime dateTime);

    DoctorAppointmentRepresentationDto cancelAppointment(Long appointmentId);

    List<DoctorAppointmentRepresentationDto> getAllDoctorsAppointmentsByDate(LocalDate localdate);

}
