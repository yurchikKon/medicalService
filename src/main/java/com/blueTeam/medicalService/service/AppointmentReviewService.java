package com.blueTeam.medicalService.service;

import com.blueTeam.medicalService.dto.user.doctor.appointment.AppointmentReviewDto;

public interface AppointmentReviewService {

    AppointmentReviewDto estimateAppointment(Long appointmentId, Double mark);

    void doctorsRateProcess();
}
