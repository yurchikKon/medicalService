package com.blueTeam.medicalService.exceptions;

public class DoctorAppointmentNotFoundException extends RuntimeException {

    public DoctorAppointmentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
