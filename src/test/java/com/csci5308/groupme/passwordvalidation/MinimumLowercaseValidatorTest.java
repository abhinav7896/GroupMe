package com.csci5308.groupme.passwordvalidation;

/**
 * @author Krutarth Patel
 */

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MinimumLowercaseValidatorTest {
	
	@Test
	public void isValidTest()
	{
		int minimumLowercase = 5;
		
		String password = "Passed";
		int cntLower = countLowercase(password);
		assertThat(cntLower >= minimumLowercase).isTrue();
		password = "Fail";
		cntLower = countLowercase(password);
		assertThat(cntLower >= minimumLowercase).isFalse();
		
	}
	
	public int countLowercase(String password)
	{
		int countLower = 0;
		for (int i=0; i < password.length(); i++)
		{
			if (Character.isLowerCase(password.charAt(i)))
			{
				countLower++;
			}
		}
		return countLower;
	}

}
