package com.csci5308.groupme.auth.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncryptionConfigImpl implements PasswordEncryptionConfig {

    private BCryptPasswordEncoder encoder;

    public PasswordEncryptionConfigImpl() {
        encoder = new BCryptPasswordEncoder();
    }

    public String encryptPassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    public boolean matches(String rawPassword, String encryptedPassword) {
        return encoder.matches(rawPassword, encryptedPassword);
    }

}
