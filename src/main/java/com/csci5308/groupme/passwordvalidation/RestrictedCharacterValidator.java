package com.csci5308.groupme.passwordvalidation;

/**
 * @author Krutarth Patel
 */

public class RestrictedCharacterValidator extends PasswordValidator{
	
	public RestrictedCharacterValidator(String constraint) {
		this.constraint = constraint;
	}

	@Override
	public boolean isValid(String password) {
		
		char[] restrictedSymbols = constraint.toCharArray();
		for (int i = 0; i < restrictedSymbols.length; i++) {
			if (password.contains(String.valueOf(restrictedSymbols[i]))){
				return false;
			}
		}
		return true;
	}

	@Override
	public String getValidatorName() {
		
		return PasswordValidatorName.RESTRICTEDSYMBOLS.toString();
	}

}
