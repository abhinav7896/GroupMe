package sql;

public class PasswordValidationQuery {
	
	 public static final String GET_ENABLED_PASSWORD_VALIDATORS = "SELECT password_validator.id, password_validator.name FROM password_validator JOIN password_validator_info ON password_validator.id = password_validator_info.validatorID WHERE password_validator_info.isEnabled=1";
	 
	 public static final String GET_CONSTRAINT_BY_VALIDATOR_ID = "SELECT value FROM password_validator_info WHERE password_validator_info.validatorID = ?";
	 
	 public static final String GET_PREVIOUS_PASSWORDS = "SELECT user.password from user_password_history join user on user_password_history.userName=user.userName where user.userName=? order by user_password_history.timestamp DESC Limit ?";
}
