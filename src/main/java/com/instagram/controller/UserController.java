package com.instagram.controller;

import com.instagram.model.User;
import com.instagram.service.UserService;
import java.util.List;

public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public boolean registerUser(User user) {
        return userService.registerUser(user);
    }

    public User login(String username, String password) {
        return userService.login(username, password);
    }

    public User getUserById(int userId) {
        return userService.getUserById(userId);
    }

    public User getUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public boolean updateUser(User user) {
        return userService.updateUser(user);
    }

    public boolean deactivateUser(int userId) {
        return userService.deactivateUser(userId);
    }
}