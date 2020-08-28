package com.csci5308.groupme.passwordvalidation;

/**
 * @author Krutarth Patel
 */

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MaximumLengthValidatorTest {
	
	@Test
	public void isValidTest()
	{
		int maximumLength = 5;
		String password = "pass";
		assertThat(password.length() <= maximumLength).isTrue();
		password = "failed";
		assertThat(password.length() <= maximumLength).isFalse();
	}

}
