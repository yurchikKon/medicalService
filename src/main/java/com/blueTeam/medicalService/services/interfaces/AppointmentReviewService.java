package com.blueTeam.medicalService.services.interfaces;

import com.blueTeam.medicalService.dto.user.doctor.appointment.AppointmentReviewDto;

public interface AppointmentReviewService {

    AppointmentReviewDto estimateAppointment(Long appointmentId, Double mark);

    void doctorsRateProcess();
}
