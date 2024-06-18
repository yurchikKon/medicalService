package com.blueTeam.medicalService.controller;

import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;
import com.blueTeam.medicalService.service.interfaces.DoctorAppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/doctorAppointments")
public class DoctorAppointmentController {
    private final DoctorAppointmentService doctorAppointmentService;

    @GetMapping("/{doctorId}/date/{date}/free")
    public List<DoctorAppointmentRepresentationDto> findAllFreeAppointmentsByDoctorIdAndDate(@PathVariable Long doctorId,
        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return doctorAppointmentService.findAllFreeAppointmentsByDoctorIdAndDate(doctorId, date);
    }

    @GetMapping("/date/{date}")
    public List<DoctorAppointmentRepresentationDto> getAllDoctorAppointments(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<DoctorAppointmentRepresentationDto> appointments = doctorAppointmentService.getAllDoctorsAppointmentRepresentationDto(date);
        return appointments;

    }
    @PostMapping("/{patientId}/{doctorId}")
    public DoctorAppointmentRepresentationDto createAppointment(@PathVariable Long patientId, @PathVariable Long doctorId,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dateTime) {
        return doctorAppointmentService.createAppointment(patientId, doctorId, dateTime);
    }

    @PutMapping("/{appointmentId}")
    public DoctorAppointmentRepresentationDto cancelAppointment(@PathVariable Long appointmentId) {
        return doctorAppointmentService.cancelAppointment(appointmentId);
    }
}
