package com.csci5308.groupme.user.controller;

import com.csci5308.groupme.user.model.User;
import com.csci5308.groupme.user.service.UserService;
import com.csci5308.groupme.user.service.UserServiceImpl;
import constants.Messages;
import errors.EditCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class UserAuthController {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String showSignUpPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "auth/signup";
    }

    @PostMapping("/signup")
    public ModelAndView signUpUser(@ModelAttribute("user") User user) {
        ModelAndView mView = new ModelAndView("auth/signup");
        String message = null;
        Map<String, String> passwordValidation = userService.passwordPolicyCheck(user);
        Integer passwordValidationStatus = Integer.parseInt(passwordValidation.get("passwordCheckStatus"));
        if (passwordValidationStatus == EditCodes.FAILURE) {
            message = passwordValidation.get("passwordPolicy") + Messages.PASSWORD_POLICY_MISMATCHED_TRAIL;
        }
        if (passwordValidationStatus != EditCodes.FAILURE) {
            int signupStatus = userService.register(user);
            if (signupStatus == EditCodes.EMAIL_EXISTS) {
                message = Messages.EMAIL_EXISTS;
            } else if (signupStatus == EditCodes.USERNAME_EXISTS) {
                message = Messages.USERNAME_EXISTS;
            } else {
                message = Messages.SIGNUP_SUCCESS;
            }
        }
        logger.info("Signing up with email: " + user.getEmail());
        mView.addObject("message", message);
        return mView;
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "auth/login";
    }

}
