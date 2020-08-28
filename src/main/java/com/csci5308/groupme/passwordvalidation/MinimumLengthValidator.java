package com.csci5308.groupme.passwordvalidation;
/**
 * @author Krutarth Patel
 */

import com.csci5308.groupme.auth.config.PasswordEncryptionConfigImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinimumLengthValidator extends PasswordValidator {

    private final Logger logger = (Logger) LoggerFactory.getLogger(PasswordEncryptionConfigImpl.class);

    public MinimumLengthValidator(String constraint) {
        this.constraint = constraint;
    }

    @Override
    public boolean isValid(String password) {

        int minimumLength = Integer.parseInt(constraint);
        int passwordLength = password.length();
        if (passwordLength < minimumLength) {
            return false;
        }
        return true;
    }

    @Override
    public String getValidatorName() {

        return PasswordValidatorName.MINIMUMLENGTH.toString();
    }

}
