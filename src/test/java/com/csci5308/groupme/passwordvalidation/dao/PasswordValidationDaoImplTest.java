package com.csci5308.groupme.passwordvalidation.dao;

/**
 * @author Krutarth Patel
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.csci5308.groupme.passwordvalidation.PasswordValidatorName;

@SpringBootTest
public class PasswordValidationDaoImplTest implements PasswordValidationDao{

	@Test
	public HashMap<Long, String> loadActivePasswordValidators() {
		HashMap<Long,String> activeValidators = new HashMap<Long, String>();
		activeValidators.put((long) 1, PasswordValidatorName.MINIMUMLENGTH.toString());
		activeValidators.put((long) 2, PasswordValidatorName.MAXIMUMLENGTH.toString());
		return activeValidators;
	}

	@Test
	public String loadConstraintByValidatorId(long validatorId) {
		if(validatorId <= 6) 
		{
			return "5";
		}
		else 
		{
			return ".,#*";
		}
	}

	@Test
	public List<String> getPreviousPasswordsByUsername(String userName, int constraint) {
		List<String> passwordList = new ArrayList<String>();
		if(userName.equals("tauser")) 
		{
			passwordList.add("fail");
		}else
		{
			passwordList.add("other");
		}
		
		for(int i = 0; i < constraint; i++) 
		{
			passwordList.add("password"+i);
		}
		return passwordList;
	}
	
	
}
