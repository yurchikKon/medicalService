package com.blueTeam.medicalService.services.interfaces;

import com.blueTeam.medicalService.dto.user.doctor.appointment.AppointmentTimeDto;
import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentCreateEditDto;
import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;
import com.blueTeam.medicalService.entities.DoctorAppointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface DoctorAppointmentService {

    List<DoctorAppointmentRepresentationDto> findAllScheduledByDoctorIdAndDate(Long id, LocalDate localDate);

    List<AppointmentTimeDto> findAllFreeAppointmentsByDoctorIdAndDate(Long id, LocalDate localDate);

    DoctorAppointmentCreateEditDto createAppointment(Long doctorId, Long patientId, LocalDate date, LocalTime time);

    DoctorAppointmentCreateEditDto cancelAppointment(Long doctorId, Long patientId, LocalDate date, LocalTime time);
}
