package com.csci5308.groupme.passwordvalidation.service;

import java.util.List;

import com.csci5308.groupme.passwordvalidation.PasswordValidator;
import com.csci5308.groupme.user.model.User;

public interface PasswordValidationService {
	
	public List<PasswordValidator> getActiveValidators(User user);

}
