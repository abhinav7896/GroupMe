package com.csci5308.groupme.passwordvalidation;

/**
 * @author Krutarth Patel
 */

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MinimumUppercaseValidatorTest {
	
	@Test
	public void isValidTest()
	{
		int minimumUppercase = 1;
		String pass = "Passed";
		int countUpper = countUppercase(pass);
		assertThat(countUpper >= minimumUppercase).isTrue();
		pass = "fail";
		countUpper = countUppercase(pass);
		assertThat(countUpper >= minimumUppercase).isFalse();
		
	}
	
	public int countUppercase(String password) 
	{	
		int countUpper = 0;
		for (int i = 0; i < password.length(); i++)
		{
			if (Character.isUpperCase(password.charAt(i))) 
			{
				countUpper++;
			}
		}
		return countUpper;
	}

}
