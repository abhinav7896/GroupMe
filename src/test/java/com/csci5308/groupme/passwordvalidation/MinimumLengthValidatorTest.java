package com.csci5308.groupme.passwordvalidation;

/**
 * @author Krutarth Patel
 */

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MinimumLengthValidatorTest {
	
	@Test
	public void isValidTest()
	{
		int minimumLength = 5;
		String password = "passed";
		assertThat(password.length() >= minimumLength).isTrue();
		password = "fail";
		assertThat(password.length() >= minimumLength).isFalse();
	}
}
