package com.csci5308.groupme.passwordvalidation;

/**
 * @author Krutarth Patel
 */

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.auth.config.PasswordEncryptionConfig;
import com.csci5308.groupme.auth.config.PasswordSecurityConfig;
import com.csci5308.groupme.course.courseadmin.instructor.service.QuestionManagerServiceImpl;
import com.csci5308.groupme.passwordvalidation.dao.PasswordValidationDao;
import com.csci5308.groupme.passwordvalidation.dao.PasswordValidationDaoImpl;
import com.csci5308.groupme.user.model.User;

import ch.qos.logback.classic.Logger;

public class PasswordHistoryValidator extends PasswordValidator{

	User user = new User();
	private final Logger logger = (Logger) LoggerFactory.getLogger(PasswordHistoryValidator.class);
	
	public PasswordHistoryValidator(String constraint, User user) {
		
		this.constraint = constraint;
		this.user = user;
	}

	@Override
	public boolean isValid(String password) {
		
		PasswordValidationDao passwordValidationDao = new PasswordValidationDaoImpl();
		PasswordEncryptionConfig passwordEncryptionConfig = SystemConfig.instance().getPasswordEncryptionConfig();

		List<String> previousPasswords = passwordValidationDao.getPreviousPasswordsByUsername(this.user.getUserName(), Integer.parseInt(constraint));
		logger.info("User is "+ this.user.getUserName());
		if(previousPasswords == null) {
			return true;
		}
		else {
			for (int i = 0; i < previousPasswords.size(); i++){
				if(passwordEncryptionConfig.matches(password, previousPasswords.get(i))) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public String getValidatorName() {
		return PasswordValidatorName.PASSWORDHISTORY.toString();
	}

}
