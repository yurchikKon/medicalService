package com.blueTeam.medicalService.services.implementations;

import com.blueTeam.medicalService.entities.DoctorAppointment;
import com.blueTeam.medicalService.entities.enums.Notification;
import com.blueTeam.medicalService.entities.enums.Status;
import com.blueTeam.medicalService.repositories.DoctorAppointmentRepository;
import com.blueTeam.medicalService.services.interfaces.DoctorAppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@Transactional
public class DoctorAppointmentServiceImpl implements DoctorAppointmentService {
    private final DoctorAppointmentRepository appointmentRepository;

    public DoctorAppointmentServiceImpl(DoctorAppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<DoctorAppointment> getPlannedAppointmentsForNotification() {
        var todayAppointments = appointmentRepository.findPlannedAppointments(Status.SCHEDULED, Notification.PLANED);
        return todayAppointments != null ? todayAppointments : Collections.emptyList();
    }

    @Override
    public void saveAllAppointments(List<DoctorAppointment> doctorAppointments) {
        appointmentRepository.saveAll(doctorAppointments);
    }

}
