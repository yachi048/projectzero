package com.instagram.controller;

import com.instagram.model.User;
import com.instagram.service.UserService;

import java.util.List;
import java.util.logging.Logger;

public class UserController {

    private static final Logger logger =
            Logger.getLogger(UserController.class.getName());

    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;

        logger.info("UserController initialized");
    }

    public boolean registerUser(User user) {

        logger.info("Register user request");

        return userService.registerUser(user);
    }

    public User login(String username, String password) {

        logger.info(
                "Login request for username: "
                        + username
        );

        return userService.login(username, password);
    }

    public User getUserById(int userId) {

        logger.info(
                "Get user request for userId: "
                        + userId
        );

        return userService.getUserById(userId);
    }

    public User getUserByUsername(String username) {

        logger.info(
                "Get user request for username: "
                        + username
        );

        return userService.getUserByUsername(username);
    }

    public List<User> getAllUsers() {

        logger.info("Get all users request");

        return userService.getAllUsers();
    }

    public boolean updateUser(User user) {

        logger.info("Update user request");

        return userService.updateUser(user);
    }

    public boolean deactivateUser(int userId) {

        logger.info(
                "Deactivate user request for userId: "
                        + userId
        );

        return userService.deactivateUser(userId);
    }
}