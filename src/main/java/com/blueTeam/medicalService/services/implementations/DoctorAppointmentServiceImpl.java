package com.blueTeam.medicalService.services.implementations;

import com.blueTeam.medicalService.entities.DoctorAppointment;
import com.blueTeam.medicalService.entities.enums.Notification;
import com.blueTeam.medicalService.entities.enums.Status;
import com.blueTeam.medicalService.repositories.DoctorAppointmentRepository;
import com.blueTeam.medicalService.services.interfaces.DoctorAppointmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Slf4j
@Service
@RequiredArgsConstructor
public class DoctorAppointmentServiceImpl implements DoctorAppointmentService {
    private final DoctorAppointmentRepository appointmentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DoctorAppointment> getPlannedAppointmentsForNotification() {
        var todayAppointments = appointmentRepository.findPlannedAppointments(Status.SCHEDULED, Notification.PLANED);
        return todayAppointments != null ? todayAppointments : Collections.emptyList();
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public List<DoctorAppointment> saveAllAppointments(List<DoctorAppointment> doctorAppointments) {
        return appointmentRepository.saveAll(doctorAppointments);
    }

}
