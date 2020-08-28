package com.csci5308.groupme.auth.service;

import com.csci5308.groupme.auth.AuthConstants;
import com.csci5308.groupme.user.model.User;

import constants.FilePathConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {

    Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailsender;

    @Override
    public boolean sendCredentials(User user) {
        try {
            Properties emailProperties = new Properties();
            Reader propertiesReader = new BufferedReader(
                    new FileReader(FilePathConstants.ENROLLMENT_EMAIL_FILE));
            emailProperties.load(propertiesReader);
            String subject = emailProperties.getProperty("subject");
            String content = emailProperties.getProperty("content") + "Username: " + user.getUserName() + "\n"
                    + "Password: " + user.getPassword();
            MimeMessage mimeMessage = javaMailsender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, false);
            messageHelper.setTo(user.getEmail());
            messageHelper.setSubject(subject);
            messageHelper.setText(content);
            javaMailsender.send(mimeMessage);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean sendPasswordRecovery(String email) {
        try {
            Properties emailProperties = new Properties();
            Reader propertiesReader = new BufferedReader(
                    new FileReader(FilePathConstants.RECOVERY_EMAIL_FILE));
            emailProperties.load(propertiesReader);
            String changePasswordLink = AuthConstants.BASE_URL + "/resetPassword?secretCode="
                    + AuthConstants.SECRET_CODE + "&email=" + email;
            String subject = emailProperties.getProperty("subject");
            String content = emailProperties.getProperty("content") + " " + changePasswordLink;
            MimeMessage mimeMessage = javaMailsender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, false);
            messageHelper.setTo(email);
            messageHelper.setSubject(subject);
            messageHelper.setText(content);
            javaMailsender.send(mimeMessage);
            return true;
        } catch (Exception e) {
            logger.info(e.getMessage());
            return false;
        }
    }
}
