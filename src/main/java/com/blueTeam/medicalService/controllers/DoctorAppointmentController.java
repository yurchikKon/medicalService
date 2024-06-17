package com.blueTeam.medicalService.controllers;

import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;
import com.blueTeam.medicalService.services.interfaces.DoctorAppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/doctorAppointments")
public class DoctorAppointmentController {
    private final DoctorAppointmentService doctorAppointmentService;

    @GetMapping
    public ResponseEntity<List<DoctorAppointmentRepresentationDto>> getAllDoctorAppointments(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
            List<DoctorAppointmentRepresentationDto> appointments = doctorAppointmentService.getAllDoctorsAppointmentRepresentationDto(date);
            return ResponseEntity.ok(appointments);

    }
}