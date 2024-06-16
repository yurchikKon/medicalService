package com.blueTeam.medicalService.controllers;

import com.blueTeam.medicalService.dto.user.doctor.appointment.AppointmentReviewDto;
import com.blueTeam.medicalService.services.interfaces.AppointmentReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/appointmentReviews")
public class AppointmentReviewController {
    private final AppointmentReviewService appointmentReviewService;

    @PostMapping("/{appointmentId}")
    public AppointmentReviewDto rateAppointment(@PathVariable Long appointmentId, @RequestParam("mark") Double mark){
        return appointmentReviewService.estimateAppointment(appointmentId, mark);
    }

}
