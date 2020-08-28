package com.csci5308.groupme.passwordvalidation.dao;
/**
 * @author Krutarth Patel
 */
import java.util.HashMap;
import java.util.List;

public interface PasswordValidationDao {
	
	public HashMap<Long,String> loadActivePasswordValidators();
	
	public String loadConstraintByValidatorId(long validatorId);
	
	public List<String> getPreviousPasswordsByUsername(String userName, int constraint);
	
}
