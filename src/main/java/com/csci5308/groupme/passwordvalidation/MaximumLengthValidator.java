package com.csci5308.groupme.passwordvalidation;

/**
 * @author Krutarth Patel
 */

public class MaximumLengthValidator extends PasswordValidator {

    public MaximumLengthValidator(String constraint) {
        this.constraint = constraint;
    }

    @Override
    public boolean isValid(String password) {

        int maximumLength = Integer.parseInt(constraint);
        int passwordLength = password.length();
        if (passwordLength > maximumLength) {
            return false;
        }
        return true;
    }

    @Override
    public String getValidatorName() {

        return PasswordValidatorName.MAXIMUMLENGTH.toString();
    }


}
