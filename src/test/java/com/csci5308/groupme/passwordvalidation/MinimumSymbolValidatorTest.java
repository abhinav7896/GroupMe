package com.csci5308.groupme.passwordvalidation;

/**
 * @author Krutarth Patel
 */

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MinimumSymbolValidatorTest {
	
	@Test
	public void isValidTest()
	{
		int minimumSymbols = 1;
		String pass = "Pas@sed";
		int cntSymbols = countSymbols(pass);
		assertThat(cntSymbols >= minimumSymbols).isTrue();
		pass = "fail";
		cntSymbols = countSymbols(pass);
		assertThat(cntSymbols >= minimumSymbols).isFalse();
		
	}
	
	public int countSymbols(String password) 
	{
		int countSymbols=0;
		char[] ch = password.toCharArray();
		for (int i = 0; i < ch.length; i++) 
		{ 
			if(Character.isLetter(ch[i]) == false 
					&& Character.isDigit(ch[i]) == false) 
			{
				countSymbols++;
			}
		}
		return countSymbols;
	}


}
