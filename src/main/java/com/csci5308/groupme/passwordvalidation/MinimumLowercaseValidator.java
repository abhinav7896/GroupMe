package com.csci5308.groupme.passwordvalidation;
/**
 * @author Krutarth Patel
 */

public class MinimumLowercaseValidator extends PasswordValidator{

	public MinimumLowercaseValidator(String constraint) {
		this.constraint = constraint;
	}

	@Override
	public boolean isValid(String password) {

		int minimumLowercase = Integer.parseInt(constraint);
		int passwordLowercase = 0;
		for (int i = 0; i < password.length(); i++) {
			if(Character.isLowerCase(password.charAt(i))){
				passwordLowercase++;
			}
		}
		if(passwordLowercase < minimumLowercase) {
			return false;
		}
		return true;
	}

	@Override
	public String getValidatorName() {

		return PasswordValidatorName.MINIMUMLOWERCASE.toString();
	}

}
