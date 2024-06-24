package com.blueTeam.medicalService.services.interfaces;

import jakarta.mail.MessagingException;

import java.util.Map;

public interface EmailSender {

    void sendMessage(String to, String subject, Map<String, Object> templateModel) throws MessagingException;

}
