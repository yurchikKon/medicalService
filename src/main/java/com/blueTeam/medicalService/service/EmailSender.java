package com.blueTeam.medicalService.service;

import jakarta.mail.MessagingException;

import java.util.Map;

public interface EmailSender {

    void sendMessage(String to, String subject, Map<String, Object> templateModel) throws MessagingException;

}
