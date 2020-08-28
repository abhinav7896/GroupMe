package com.csci5308.groupme.passwordvalidation;

/**
 * @author Krutarth Patel
 */

public class MinimumUppercaseValidator extends PasswordValidator {

	public MinimumUppercaseValidator(String constraint) {
		this.constraint = constraint;
	}
	
	@Override
	public boolean isValid(String password) {
		
		int minimumUppercase = Integer.parseInt(constraint);
		int passwordUppercase = 0;
		for (int i = 0; i < password.length(); i++) {
			if(Character.isUpperCase(password.charAt(i))) {
				passwordUppercase++;
			}
		}
		if (passwordUppercase < minimumUppercase) {
			return false;
		}
		return true;
	}

	@Override
	public String getValidatorName() {
		
		return PasswordValidatorName.MINIMUMUPPERCASE.toString();
	}

}
