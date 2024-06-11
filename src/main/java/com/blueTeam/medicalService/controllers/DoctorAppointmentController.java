package com.blueTeam.medicalService.controllers;

import com.blueTeam.medicalService.dto.user.doctor.appointment.AppointmentTimeDto;
import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentCreateEditDto;
import com.blueTeam.medicalService.services.interfaces.DoctorAppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/doctorAppointments")
public class DoctorAppointmentController {
    private final DoctorAppointmentService doctorAppointmentService;

    @GetMapping("/{doctorId}/date/{date}/free")
    public List<AppointmentTimeDto> findAllFreeAppointmentsByDoctorIdAndDate(@PathVariable Long doctorId,
        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return doctorAppointmentService.findAllFreeAppointmentsByDoctorIdAndDate(doctorId, date);
    }

    @PostMapping
    public DoctorAppointmentCreateEditDto createAppointment(@RequestBody DoctorAppointmentCreateEditDto dto) {
        return doctorAppointmentService.createAppointment(dto);
    }

    @PutMapping("/{appointmentId}")
    public DoctorAppointmentCreateEditDto cancelAppointment(@PathVariable Long appointmentId) {
        return doctorAppointmentService.cancelAppointment(appointmentId);
    }
}
