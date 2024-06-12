package com.blueTeam.medicalService.services.interfaces;


import com.blueTeam.medicalService.entities.DoctorAppointment;

import java.util.List;

public interface DoctorAppointmentService {
    List<DoctorAppointment> getPlannedAppointmentsForNotification();

    void saveAllAppointments(List<DoctorAppointment> doctorAppointments);
}
