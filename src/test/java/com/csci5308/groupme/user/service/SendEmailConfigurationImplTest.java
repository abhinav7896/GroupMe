package com.csci5308.groupme.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SendEmailConfigurationImplTest {

    EmailConfigurationMockTest emailConfigurationMock = new EmailConfigurationMockTest();

    @Test
    public void initiateEmailSenderTest() {
        JavaMailSenderImpl mailSender = emailConfigurationMock.getMailSender();
        Properties properties = emailConfigurationMock.getProperties();
        assertEquals("smtp.gmail.com", mailSender.getHost());
        assertEquals(587, mailSender.getPort());
        assertEquals("groupmecsci5308@gmail.com", mailSender.getUsername());
        assertEquals("groupme@CSCI5308", mailSender.getPassword());
        assertEquals("smtp", properties.getProperty("mail.transport.protocal"));
        assertEquals("true", properties.getProperty("mail.smtp.starttls.enable"));
    }

}
