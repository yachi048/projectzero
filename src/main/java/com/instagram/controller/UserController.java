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
        // TODO: Call UserService
        return false;
    }

    public User login(String username, String password) {
        // TODO: Call UserService
        return null;
    }

    public User getUserById(int userId) {
        // TODO: Call UserService
        return null;
    }

    public User getUserByUsername(String username) {
        // TODO: Call UserService
        return null;
    }

    public List<User> getAllUsers() {
        // TODO: Call UserService
        return null;
    }

    public boolean updateUser(User user) {
        // TODO: Call UserService
        return false;
    }

    public boolean deactivateUser(int userId) {
        // TODO: Call UserService
        return false;
    }
}