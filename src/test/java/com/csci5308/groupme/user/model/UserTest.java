package com.csci5308.groupme.user.model;

import constants.Roles;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    User user = new UserMock().getUser();

    @Test
    public void getUserNameTest() {
        assertEquals("kharechaB00", user.getUserName());
    }

    @Test
    public void getFirstNameTest() {
        assertEquals("Karan", user.getFirstName());
    }

    @Test
    public void getLastNameTest() {
        assertEquals("Kharecha", user.getLastName());
    }

    @Test
    public void getEmailaddressTest() {
        assertEquals("kharechakaran67@gmail.com", user.getEmail());
    }

    @Test
    public void getPasswordTest() {
        assertEquals("B00xxxxxx", user.getPassword());
    }

    @Test
    public void getRolesTest() {
        List<String> roles = new ArrayList<>();
        roles.add(Roles.STUDENT);
        user.setRoles(roles);
        assertEquals(roles, user.getRoles());
    }

    @Test
    public void getPermissionsTest() {
        List<String> permissions = new ArrayList<>();
        permissions.add("permitted");
        user.setPermissions(permissions);
        assertEquals(permissions, user.getPermissions());
    }


    @Test
    public void setUserNameTest() {
        User user = new User();
        user.setUserName("kharechaB00");
        assertEquals("kharechaB00", user.getUserName());
    }

    @Test
    public void setFirstNameTest() {
        User user = new User();
        user.setFirstName("Karan");
        assertEquals("Karan", user.getFirstName());
    }

    @Test
    public void setLastnameTest() {
        User user = new User();
        user.setLastName("Kharecha");
        assertEquals("Kharecha", user.getLastName());
    }


    @Test
    public void setEmailaddressTest() {
        User user = new User();
        user.setEmail("kharechakaran67@gmail.com");
        assertEquals("kharechakaran67@gmail.com", user.getEmail());
    }

    @Test
    public void setPasswordTest() {
        User user = new User();
        user.setPassword("B00xxxxxx");
        assertEquals("B00xxxxxx", user.getPassword());
    }

    @Test
    public void setRolesTest() {
        User user = new User();
        List<String> userRoles = new ArrayList<>();
        userRoles.add(Roles.STUDENT);
        user.setRoles(userRoles);
        List<String> roles = new ArrayList<>();
        roles.add(Roles.STUDENT);
        user.setRoles(roles);
        assertEquals(roles, user.getRoles());
    }

    @Test
    public void setPermissionsTest() {
        User user = new User();
        List<String> userPermissions = new ArrayList<>();
        userPermissions.add("permitted");
        user.setPermissions(userPermissions);
        List<String> permissions = new ArrayList<>();
        permissions.add("permitted");
        user.setPermissions(permissions);
        assertEquals(permissions, user.getPermissions());
    }

}