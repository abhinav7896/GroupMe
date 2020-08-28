package com.csci5308.groupme.auth.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PasswordSecurityConfigTest {
	
	@Test
	public String encryptPasswordTest(String rawPassword)
	{
		return "encrypted";
	}
	
	@Test
	public boolean matchesTest(String rawPassword, String encryptedPassword)
	{
		if (null == rawPassword || rawPassword.isEmpty())
		{
			return false;
		}
		if (null == encryptedPassword || encryptedPassword.isEmpty())
		{
			return false;
		}
		return encryptedPassword.equals("encrypted");
	}

}
