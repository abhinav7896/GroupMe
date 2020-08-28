package com.csci5308.groupme.user.model;

import constants.Roles;

import java.util.ArrayList;
import java.util.List;

public class UserMock {

    User user;

    public UserMock() {
        user = new User();
        user.setUserName("kharechaB00");
        user.setLastName("Kharecha");
        user.setFirstName("Karan");
        user.setEmail("kharechakaran67@gmail.com");
        user.setPassword("B00xxxxxx");
        List<String> roles = new ArrayList<>();
        roles.add(Roles.STUDENT);
        user.setRoles(roles);
        List<String> permissions = new ArrayList<>();
        permissions.add("permitted");
        user.setRoles(permissions);
    }

    public User getUser() {
        return user;
    }
}
