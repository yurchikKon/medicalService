package com.blueTeam.medicalService.services.interfaces;

import com.blueTeam.medicalService.dto.doctorsAppointment.AppointmentTimeDto;
import com.blueTeam.medicalService.entities.DoctorsAppointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface DoctorsAppointmentService {

    List<DoctorsAppointment> findAllByDoctorIdAndDate(Long id, LocalDate localDate);
    List<AppointmentTimeDto> findAllFreeAppointmentsByDoctorIdAndDate(Long id, LocalDate localDate);

    DoctorsAppointment createAppointment(Long id, LocalDate date, LocalTime time);
}
