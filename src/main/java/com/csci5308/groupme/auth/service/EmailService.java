package com.csci5308.groupme.auth.service;

import com.csci5308.groupme.user.model.User;

public interface EmailService {

    public boolean sendCredentials(User user);

    public boolean sendPasswordRecovery(String email);

}
