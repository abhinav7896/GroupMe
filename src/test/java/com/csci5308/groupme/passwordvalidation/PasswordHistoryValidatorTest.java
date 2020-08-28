package com.csci5308.groupme.passwordvalidation;

/**
 * @author Krutarth Patel
 */

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.csci5308.groupme.passwordvalidation.dao.PasswordValidationDaoImplTest;

@SpringBootTest
public class PasswordHistoryValidatorTest {
	
	@Test
	public void isValidTest()
	{
		PasswordValidationDaoImplTest validatorDB = new PasswordValidationDaoImplTest();
		int historyCount = 3;
		String password = "pass"; 
		List<String> passwordList = validatorDB.getPreviousPasswordsByUsername("savani", historyCount);
		assertThat(passwordList.contains(password) == false).isTrue();
		password = "fail";
		passwordList = validatorDB.getPreviousPasswordsByUsername("abc", historyCount);
		assertThat(passwordList.contains(password) == true).isFalse();
	}

}
