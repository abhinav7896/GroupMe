package com.csci5308.groupme.passwordvalidation.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.csci5308.groupme.passwordvalidation.dao.PasswordValidationDao;
import com.csci5308.groupme.passwordvalidation.dao.PasswordValidationDaoImplTest;

@SpringBootTest
public class PasswordValidationServiceImplTest {
	
	@Test
	public void getActiveValidators() 
	{
		PasswordValidationDaoImplTest validatorDB = new PasswordValidationDaoImplTest();
		HashMap<Long, String> activeValidators = validatorDB.loadActivePasswordValidators();
		assertThat(activeValidators.size()>0).isTrue();
		assertThat(activeValidators.size()==2).isTrue();
		for (@SuppressWarnings("rawtypes") Entry<Long, String> item : activeValidators.entrySet()) 
		{
			String constraint = validatorDB.loadConstraintByValidatorId((long) item.getKey());
			assertThat(constraint.length() > 0).isTrue();
			assertThat(constraint).isNotBlank();
			assertThat(constraint).isNotEmpty();
		}

	}
	

}
