package com.blueTeam.medicalService.controllers;

import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;
import com.blueTeam.medicalService.services.interfaces.DoctorAppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/doctorAppointments")
public class DoctorAppointmentController {
    private final DoctorAppointmentService doctorAppointmentService;

    @GetMapping
    public ResponseEntity<List<DoctorAppointmentRepresentationDto>> getDoctorAppointmentsByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) {
        List<DoctorAppointmentRepresentationDto> doctorAppointments = doctorAppointmentService.getDoctorAppointmentRepresentationDto(dateTime);
        return new ResponseEntity<>(doctorAppointments, HttpStatus.OK);
    }
}
