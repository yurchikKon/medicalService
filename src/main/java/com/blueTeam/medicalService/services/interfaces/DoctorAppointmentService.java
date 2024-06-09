package com.blueTeam.medicalService.services.interfaces;

import com.blueTeam.medicalService.dto.doctorsAppointment.AppointmentTimeDto;
import com.blueTeam.medicalService.entities.DoctorAppointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface DoctorAppointmentService {

    List<DoctorAppointment> findAllByDoctorIdAndDate(Long id, LocalDate localDate);
    List<AppointmentTimeDto> findAllFreeAppointmentsByDoctorIdAndDate(Long id, LocalDate localDate);

    DoctorAppointment createAppointment(Long doctorId, Long patientId, LocalDate date, LocalTime time);
}
