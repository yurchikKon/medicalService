package com.blueTeam.medicalService.controllers;

import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;
import com.blueTeam.medicalService.services.interfaces.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/patients")
public class PatientController {
    private final PatientService patientService;

    @GetMapping("/{id}/active-appointments")
    public List<DoctorAppointmentRepresentationDto> getActiveAppointments(@PathVariable long id) {
        return patientService.getActivePatientAppointmentDto(id);
    }
}
