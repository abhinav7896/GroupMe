package com.csci5308.groupme.auth.config;

public interface PasswordEncryptionConfig {

    public String encryptPassword(String rawPassword);

    public boolean matches(String rawPassword, String encryptedPassword);

}
