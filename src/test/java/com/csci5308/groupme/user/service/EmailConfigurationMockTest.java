package com.csci5308.groupme.user.service;

import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class EmailConfigurationMockTest {

    JavaMailSenderImpl mailSender;
    Properties properties;

    public EmailConfigurationMockTest() {
        mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("groupmecsci5308@gmail.com");
        mailSender.setPassword("groupme@CSCI5308");
        properties = mailSender.getJavaMailProperties();
        properties.put("mail.transport.protocal", "smtp");
        properties.put("mail.smtp.starttls.enable", "true");
    }

    public JavaMailSenderImpl getMailSender() {
        return mailSender;
    }

    public Properties getProperties() {
        return properties;
    }
}
