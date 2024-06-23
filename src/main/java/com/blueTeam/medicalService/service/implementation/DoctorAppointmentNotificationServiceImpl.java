package com.blueTeam.medicalService.services.implementations;

import com.blueTeam.medicalService.entities.DoctorAppointment;
import com.blueTeam.medicalService.entities.enums.Notification;
import com.blueTeam.medicalService.services.interfaces.DoctorAppointmentNotificationService;
import com.blueTeam.medicalService.services.interfaces.DoctorAppointmentService;
import com.blueTeam.medicalService.services.interfaces.EmailSender;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class DoctorAppointmentNotificationServiceImpl implements DoctorAppointmentNotificationService {
    private final DoctorAppointmentService appointmentService;
    private final EmailSender emailSenderImpl;

    @Value("${properties.email.subject:'Прием у врача'}")
    private String emailSubject;

    @Override
    @Scheduled(cron = "${properties.scheduled.cron}")
    public void notifyPatients() {
        var todayAppointments = appointmentService.getPlannedAppointmentsForNotification();
        if (todayAppointments.isEmpty()) {
            return;
        }
        sendEmailNotification(todayAppointments);
        notifyPatients();
    }

    private void sendEmailNotification(List<DoctorAppointment> appointments) {
        try {
            for (var appointment : appointments) {
                emailSenderImpl.sendMessage(appointment.getPatient().getEmail(), emailSubject, getDataForEmailTemplate(appointment));
                appointment.setNotification(Notification.DONE);
            }
        } catch (MessagingException e) {
            log.error(e.getMessage());
        } finally {
            appointmentService.saveAllAppointments(appointments);
        }
    }

    private Map<String, Object> getDataForEmailTemplate(DoctorAppointment appointment) {
        var templateModel = new HashMap<String, Object>();
        templateModel.put("patientLastName", appointment.getPatient().getLastName());
        templateModel.put("patientFirstName", appointment.getPatient().getFirstName());
        templateModel.put("doctorLastName", appointment.getDoctor().getLastName());
        templateModel.put("doctorFirstName", appointment.getDoctor().getFirstName());
        templateModel.put("appointmentTime", appointment.getDateTime());
        return templateModel;
    }

}
