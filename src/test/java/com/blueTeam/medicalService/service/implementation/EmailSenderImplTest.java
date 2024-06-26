package com.blueTeam.medicalService.service.implementation;

import com.blueTeam.medicalService.service.EmailSender;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = EmailSenderImpl.class)
class EmailSenderImplTest {

    @Autowired
    private EmailSender emailSender;

    @MockBean
    private JavaMailSender javaMailSender;

    @MockBean
    private SpringTemplateEngine templateEngine;

    @Test
    void shouldSendMessage() throws MessagingException {
        String to = "test@example.com";
        String subject = "Test Subject";
        Map<String, Object> templateModel = new HashMap<>();
        MimeMessage mimeMessage = new MimeMessage((Session) null);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
        when(templateEngine.process(anyString(), any(Context.class))).thenReturn("HTML body");

        emailSender.sendMessage(to, subject, templateModel);

        verify(javaMailSender, times(1)).createMimeMessage();
        verify(templateEngine, times(1)).process(anyString(), any(Context.class));
        verify(javaMailSender, times(1)).send(any(MimeMessage.class));
        assertEquals(to, mimeMessage.getRecipients(MimeMessage.RecipientType.TO)[0].toString());
        assertEquals(subject, mimeMessage.getSubject());
    }
}
