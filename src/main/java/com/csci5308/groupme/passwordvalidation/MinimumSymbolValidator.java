package com.csci5308.groupme.passwordvalidation;
/**
 * @author Krutarth Patel
 */

public class MinimumSymbolValidator extends PasswordValidator{

	public MinimumSymbolValidator(String constraint) {
		this.constraint = constraint;
	}
	
	@Override
	public boolean isValid(String password) {
		
		int minimumSymbols = Integer.parseInt(constraint);
		int passwordSymbols = 0;
		for (int i = 0; i < password.length(); i++) {
			if(Character.isLetter(password.charAt(i)) == false && Character.isDigit(password.charAt(i)) == false) {
				passwordSymbols++;
			}
		}
		if (passwordSymbols < minimumSymbols) {
			return false;
		}
		return false;
	}

	@Override
	public String getValidatorName() {
		
		return PasswordValidatorName.MINIMUMSYMBOLS.toString();
	}

}
