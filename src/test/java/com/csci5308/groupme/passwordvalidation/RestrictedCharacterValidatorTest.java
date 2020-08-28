package com.csci5308.groupme.passwordvalidation;

/**
 * @author Krutarth Patel
 */

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RestrictedCharacterValidatorTest {
	
	@Test
	public void isValidTest()
	{
		String restrictedCharacter = "#";
		String pass = "Pas@sed";
		assertThat(pass.contains(restrictedCharacter) == false).isTrue();
		pass = "Fai#led";
		assertThat(pass.contains(restrictedCharacter) == false).isFalse();
		
	}

}
