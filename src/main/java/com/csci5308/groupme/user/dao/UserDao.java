package com.csci5308.groupme.user.dao;

import com.csci5308.groupme.user.model.User;

import java.util.List;

public interface UserDao {

    public User findByUserName(String userName);

    public User findByEmail(String email);

    public List<User> findByName(String firstName, String lastName);

    public int save(User user);

    public int addRole(String userName, String roleName);

    public int update(User user);

}
