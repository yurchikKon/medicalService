package com.blueTeam.medicalService.service.implementation;

import com.blueTeam.medicalService.service.EmailSender;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Locale;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailSenderImpl implements EmailSender {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    @Value("${app.email.template:mail-template}")
    private String mailTemplate;

    @Override
    public void sendMessage(String to, String subject, Map<String, Object> templateModel) throws MessagingException {
        var message = javaMailSender.createMimeMessage();
        var helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(createHtmlBody(templateModel), true);
        javaMailSender.send(message);
    }

    private String createHtmlBody(Map<String, Object> modelTemplate) {
        var context = new Context(new Locale("ru", "RU"));
        context.setVariables(modelTemplate);
        return templateEngine.process(mailTemplate, context);
    }

}
