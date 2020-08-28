package com.csci5308.groupme.user.service;

import com.csci5308.groupme.user.dao.UserDao;
import com.csci5308.groupme.user.model.User;
import errors.EditCodes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserDao userDao;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Test
    public void registerUserTest() {
        User user = new User();
        String rawPassword = "password";
        user.setUserName("testuser123");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("test_user@gmail.com");
        user.setPassword(rawPassword);
        when(userDao.findByEmail(user.getEmail())).thenReturn(null);
        when(userDao.save(user)).thenReturn(1);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("$21wyp882e^8");
        int registration = userServiceImpl.register(user);
        String encodedPassword = user.getPassword();
        assertNotEquals(rawPassword, encodedPassword);
        assertEquals(1, registration);
    }

    @Test
    public void userNameAlreadytExistsTest() {
        User user = new User();
        user.setUserName("testuser123");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("test_user@gmail.com");
        user.setPassword("password");
        when(userDao.findByEmail(user.getEmail())).thenReturn(null);
        when(userDao.save(user)).thenReturn(EditCodes.USERNAME_EXISTS);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("$21wyp882e^8");
        int registration = userServiceImpl.register(user);
        assertEquals(EditCodes.USERNAME_EXISTS, registration);
    }

    @Test
    public void emailAlreadytExistsTest() {
        User user = new User();
        user.setUserName("testuser123");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("test_user@gmail.com");
        user.setPassword("password");
        when(userDao.findByEmail(user.getEmail())).thenReturn(user);
        int registration = userServiceImpl.register(user);
        assertEquals(EditCodes.EMAIL_EXISTS, registration);
    }

    @Test
    public void updatePasswordTest() {
        String rawNewPassword = "newpassword";
        User user = new User();
        user.setUserName("testuser123");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("test_user@gmail.com");
        user.setPassword("oldpassword");
        when(userDao.findByEmail(user.getEmail())).thenReturn(user);
        when(userDao.update(user)).thenReturn(1);
        when(passwordEncoder.encode(rawNewPassword)).thenReturn("$21wyp882e^8");
        int updateStatus = userServiceImpl.updatePassword("test_user@gmail.com", rawNewPassword);
        String encodedNewPassword = user.getPassword();
        assertNotEquals(rawNewPassword, encodedNewPassword);
        assertEquals(1, updateStatus);
    }

    @Test
    public void updatePasswordNoEmailTest() {
        String rawNewPassword = "newpassword";
        User user = new User();
        user.setUserName("testuser123");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("test_user@gmail.com");
        user.setPassword("oldpassword");
        when(userDao.findByEmail(user.getEmail())).thenReturn(null);
        int updateStatus = userServiceImpl.updatePassword("test_user@gmail.com", rawNewPassword);
        assertEquals(EditCodes.EMAIL_DOES_NOT_EXIST, updateStatus);
    }

}
