package com.csci5308.groupme.auth.config;

import com.csci5308.groupme.SystemConfig;
import org.omg.CORBA.INTERNAL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailSenderConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        String hostname = System.getenv("HOSTNAME");
        String port = System.getenv("PORT");
        String email = System.getenv("EMAIL");
        String password = System.getenv("EMAIL_PASSWORD");
        mailSender.setHost(hostname);
        mailSender.setPort(Integer.parseInt(port));
        mailSender.setUsername(email);
        mailSender.setPassword(password);
        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.starttls.enable", "true");
        return mailSender;
    }
}
