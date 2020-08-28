package com.csci5308.groupme.passwordvalidation.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csci5308.groupme.passwordvalidation.MaximumLengthValidator;
import com.csci5308.groupme.passwordvalidation.MinimumLengthValidator;
import com.csci5308.groupme.passwordvalidation.MinimumLowercaseValidator;
import com.csci5308.groupme.passwordvalidation.MinimumSymbolValidator;
import com.csci5308.groupme.passwordvalidation.MinimumUppercaseValidator;
import com.csci5308.groupme.passwordvalidation.PasswordHistoryValidator;
import com.csci5308.groupme.passwordvalidation.PasswordValidator;
import com.csci5308.groupme.passwordvalidation.PasswordValidatorName;
import com.csci5308.groupme.passwordvalidation.RestrictedCharacterValidator;
import com.csci5308.groupme.passwordvalidation.dao.PasswordValidationDao;
import com.csci5308.groupme.user.model.User;

public class PasswordValidationServiceImpl implements PasswordValidationService{
	
	Logger logger = LoggerFactory.getLogger(PasswordValidationServiceImpl.class);
	
	private final PasswordValidationDao passwordValidationDao;
	private List<PasswordValidator> activeValidators;
	private HashMap<Long,String> validators;
	
    public PasswordValidationServiceImpl(PasswordValidationDao passwordValidationDao) {
        this.passwordValidationDao = passwordValidationDao;
        activeValidators = new ArrayList<PasswordValidator>();
		validators = passwordValidationDao.loadActivePasswordValidators();
    }

	@Override
	public List<PasswordValidator> getActiveValidators(User user) {
		
		activeValidators = new ArrayList<PasswordValidator>();
		for (@SuppressWarnings("rawtypes") Map.Entry item : validators.entrySet()) 
		{
			long key = (long) item.getKey();
			String value = (String) item.getValue();
			String constraint = passwordValidationDao.loadConstraintByValidatorId(key);
	        
			if(value.equals(PasswordValidatorName.MINIMUMLENGTH.toString())) {
	        	activeValidators.add(new MinimumLengthValidator(constraint));
	        }
			else if(value.equals(PasswordValidatorName.MAXIMUMLENGTH.toString())) {
	        	activeValidators.add(new MaximumLengthValidator(constraint));
	        }
			else if(value.equals(PasswordValidatorName.MINIMUMUPPERCASE.toString())) {
	        	activeValidators.add(new MinimumUppercaseValidator(constraint));
	        }
			else if(value.equals(PasswordValidatorName.MINIMUMLOWERCASE.toString())) {
	        	activeValidators.add(new MinimumLowercaseValidator(constraint));
	        }
			else if(value.equals(PasswordValidatorName.MINIMUMSYMBOLS.toString())) {
	        	activeValidators.add(new MinimumSymbolValidator(constraint));
	        }
			else if(value.equals(PasswordValidatorName.RESTRICTEDSYMBOLS.toString())) {
	        	activeValidators.add(new RestrictedCharacterValidator(constraint));
	        }
			else if(value.equals(PasswordValidatorName.PASSWORDHISTORY.toString())) 
	        {
	        	activeValidators.add(new PasswordHistoryValidator(constraint, user));
	        }   
 
		}
		logger.info("List of active validators are: "+ activeValidators);
		return activeValidators;
	}

}
