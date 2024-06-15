package com.blueTeam.medicalService.controllers;

import com.blueTeam.medicalService.dto.user.doctor.DoctorRepresentationDto;
import com.blueTeam.medicalService.services.interfaces.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/{id}/doctorInfo")
    public DoctorRepresentationDto getDoctorInfoDto(@PathVariable Long doctorId) {
        return doctorService.getDoctorInfoDto(doctorId);
    }
}

