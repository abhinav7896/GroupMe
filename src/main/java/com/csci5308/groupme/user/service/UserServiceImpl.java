package com.csci5308.groupme.user.service;

import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.passwordvalidation.PasswordValidator;
import com.csci5308.groupme.passwordvalidation.service.PasswordValidationService;
import com.csci5308.groupme.user.dao.UserDao;
import com.csci5308.groupme.user.model.User;
import com.csci5308.groupme.user.model.UserAuthDetails;
import errors.EditCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private PasswordValidationService passwordValidationService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDao.findByUserName(userName);
        return new UserAuthDetails(user);
    }

    @Override
    public User getByUserName(String userName) {
        User user = userDao.findByUserName(userName);
        return user;
    }

    @Override
    public User getByEmail(String email) {
        User user = userDao.findByEmail(email);
        return user;
    }

    @Override
    public int register(User user) {
        int insertStatus = 0;
        String rawPassword = user.getPassword();
        if (null != userDao.findByEmail(user.getEmail())) {
            insertStatus = EditCodes.EMAIL_EXISTS;
        } else {
            logger.info("Encoding the password for {}", user.getUserName());
            user.setPassword(passwordEncoder.encode(rawPassword));
            insertStatus = userDao.save(user);
        }
        return insertStatus;
    }

    @Override
    public int addRole(String userName, String roleName) {
        int status = userDao.addRole(userName, roleName);
        return status;
    }

    @Override
    public int updatePassword(String email, String newPassword) {
        int updateStatus = 0;
        User user = this.getByEmail(email);
        if (null == user)
            updateStatus = EditCodes.EMAIL_DOES_NOT_EXIST;
        else {
            user.setPassword(passwordEncoder.encode(newPassword));
            updateStatus = userDao.update(user);
            if (updateStatus == EditCodes.SUCCESS)
                logger.info("Password updated.....");
        }
        return updateStatus;
    }

    @Override
    public Map<String, String> passwordPolicyCheck(User user) {
        Integer passwordCheckStatus = 1;
        passwordValidationService = SystemConfig.instance().getPasswordValidationService();
        String rawPassword = user.getPassword();
        List<PasswordValidator> passwordValidators = passwordValidationService.getActiveValidators(user);
        Map<String, String> statusCheck = new HashMap<>();
        StringBuilder buildFailedPasswordPoliciesString = new StringBuilder();
        for (int i = 0; i < passwordValidators.size(); i++) {
            PasswordValidator validator = passwordValidators.get(i);
            if (validator.isValid(rawPassword) == false) {
                logger.info("Password criteria not met!");
                logger.info(validator.getValidatorName() + " is not satisfied with the constraint of " + validator.constraint);
                String passwordPolicy = validator.getValidatorName();
                passwordCheckStatus = EditCodes.FAILURE;
                buildFailedPasswordPoliciesString.append(passwordPolicy + "\n");
            }
        }
        if (passwordCheckStatus == EditCodes.FAILURE) {
            statusCheck.put("passwordPolicy", buildFailedPasswordPoliciesString.toString());
        }
        statusCheck.put("passwordCheckStatus", passwordCheckStatus.toString());
        return statusCheck;
    }

}
