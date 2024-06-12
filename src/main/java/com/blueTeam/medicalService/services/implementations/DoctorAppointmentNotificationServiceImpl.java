package com.blueTeam.medicalService.services.implementations;

import com.blueTeam.medicalService.entities.DoctorAppointment;
import com.blueTeam.medicalService.entities.enums.Notification;
import com.blueTeam.medicalService.services.interfaces.DoctorAppointmentNotificationService;
import com.blueTeam.medicalService.services.interfaces.DoctorAppointmentService;
import com.blueTeam.medicalService.services.interfaces.EmailSender;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
public class DoctorAppointmentNotificationServiceImpl implements DoctorAppointmentNotificationService {
    private final DoctorAppointmentService appointmentService;
    private final EmailSender emailSenderImpl;

    private static final String EMAIL_SUBJECT = "Doctor Appointment Notification";

    public DoctorAppointmentNotificationServiceImpl(DoctorAppointmentService appointmentRepository, EmailSender emailSenderImpl) {
        this.appointmentService = appointmentRepository;
        this.emailSenderImpl = emailSenderImpl;
    }

    @Override
    @Scheduled(cron = "0 0 8 * * ?")
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
                emailSenderImpl.sendMessage(appointment.getPatient().getEmail(), EMAIL_SUBJECT, getDataForEmailTemplate(appointment));
                appointment.setNotification(Notification.DONE);
            }
        } catch (MessagingException e) {
            log.error(e.getMessage());
        } finally {
            appointmentService.saveAllAppointments(appointments);
        }
    }

    private Map<String, Object> getDataForEmailTemplate(DoctorAppointment appointment) {
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("patientLastName", appointment.getPatient().getLastName());
        templateModel.put("patientFirstName", appointment.getPatient().getFirstName());
        templateModel.put("doctorLastName", appointment.getDoctor().getLastName());
        templateModel.put("doctorFirstName", appointment.getDoctor().getFirstName());
        templateModel.put("appointmentTime", appointment.getDateTime());
        return templateModel;
    }

}
