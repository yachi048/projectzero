package com.instagram.service;

import com.instagram.model.User;

import java.util.List;

public interface UserService {

    boolean registerUser(User user);

    User login(String username, String password);

    User getUserById(int userId);

    User getUserByUsername(String username);

    List<User> getAllUsers();

    boolean updateUser(User user);

    boolean deactivateUser(int userId);
}