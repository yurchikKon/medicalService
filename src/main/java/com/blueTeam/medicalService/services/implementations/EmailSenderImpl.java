package com.blueTeam.medicalService.services.implementations;

import com.blueTeam.medicalService.services.interfaces.EmailSender;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Locale;
import java.util.Map;

@Slf4j
@Service
public class EmailSenderImpl implements EmailSender {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    public EmailSenderImpl(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendMessage(String to, String subject, Map<String, Object> templateModel) throws MessagingException {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(createHtmlBody(templateModel), true);
            mailSender.send(message);
    }

    private String createHtmlBody(Map<String, Object> templateModel) {
        Context context = new Context(new Locale("ru", "RU"));
        context.setVariables(templateModel);
        return templateEngine.process("mail-template", context);
    }

}
