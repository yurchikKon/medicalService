package com.blueTeam.medicalService.controller;

import com.blueTeam.medicalService.dto.user.doctor.DoctorRepresentationDto;
import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;
import com.blueTeam.medicalService.service.DoctorAppointmentService;
import com.blueTeam.medicalService.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/doctors")
public class DoctorController {

    private final DoctorAppointmentService doctorAppointmentService;
    private final DoctorService doctorService;

    @GetMapping("/{doctorId}/appointments/date/{date}")
    public List<DoctorAppointmentRepresentationDto> findAllScheduledByDoctorIdAndDate(
            @PathVariable Long doctorId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return doctorAppointmentService.findAllScheduledByDoctorIdAndDate(doctorId, date);
    }


    @GetMapping("/{doctorId}/doctor-info")
    public DoctorRepresentationDto getDoctorInfoDto(@PathVariable Long doctorId) {
        return doctorService.getDoctorInfoDto(doctorId);
    }
}