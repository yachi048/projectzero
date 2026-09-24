package com.instagram.service;

import com.instagram.dao.UserDAO;
import com.instagram.dao.UserDAOImpl;
import com.instagram.model.User;

import java.util.List;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {

    private static final Logger logger =
            Logger.getLogger(UserServiceImpl.class.getName());

    private final UserDAO userDAO;

    public UserServiceImpl() {

        this.userDAO = new UserDAOImpl();

        logger.info("UserServiceImpl initialized");
    }

    @Override
    public boolean registerUser(User user) {

        logger.info("Register user request received");

        if (user == null) {
            logger.warning("User is null");
            return false;
        }

        if (user.getUsername() == null
                || user.getUsername().trim().isEmpty()) {

            logger.warning("Username is required");
            return false;
        }

        if (user.getEmail() == null
                || user.getEmail().trim().isEmpty()) {

            logger.warning("Email is required");
            return false;
        }

        if (user.getPasswordHash() == null
                || user.getPasswordHash().trim().isEmpty()) {

            logger.warning("Password is required");
            return false;
        }

        if (userDAO.existsByUsernameOrEmail(
                user.getUsername(),
                user.getEmail())) {

            logger.warning(
                    "Username or email already exists: "
                            + user.getUsername()
            );

            return false;
        }

        boolean result = userDAO.addUser(user);

        if (result) {
            logger.info(
                    "User registered successfully: "
                            + user.getUsername()
            );
        } else {
            logger.warning(
                    "User registration failed: "
                            + user.getUsername()
            );
        }

        return result;
    }

    @Override
    public User login(String username, String password) {

        logger.info(
                "Login request received for username: "
                        + username
        );

        User user = userDAO.getUserByUsername(username);

        if (user != null
                && user.getPasswordHash().equals(password)
                && "ACTIVE".equalsIgnoreCase(user.getStatus())) {

            logger.info(
                    "Login successful for username: "
                            + username
            );

            return user;
        }

        logger.warning(
                "Login failed for username: "
                        + username
        );

        return null;
    }

    @Override
    public User getUserById(int userId) {

        logger.info(
                "Get user request for userId: "
                        + userId
        );

        User user = userDAO.getUserById(userId);

        if (user != null) {
            logger.info(
                    "User found for userId: "
                            + userId
            );
        } else {
            logger.warning(
                    "User not found for userId: "
                            + userId
            );
        }

        return user;
    }

    @Override
    public User getUserByUsername(String username) {

        logger.info(
                "Get user request for username: "
                        + username
        );

        User user = userDAO.getUserByUsername(username);

        if (user != null) {
            logger.info(
                    "User found for username: "
                            + username
            );
        } else {
            logger.warning(
                    "User not found for username: "
                            + username
            );
        }

        return user;
    }

    @Override
    public List<User> getAllUsers() {

        logger.info("Get all users request");

        List<User> users = userDAO.getAllUsers();

        logger.info(
                "Total users retrieved: "
                        + users.size()
        );

        return users;
    }

    @Override
    public boolean updateUser(User user) {

        logger.info("Update user request received");

        if (user == null) {
            logger.warning("User is null");
            return false;
        }

        boolean result = userDAO.updateUser(user);

        if (result) {
            logger.info(
                    "User updated successfully: "
                            + user.getUserId()
            );
        } else {
            logger.warning(
                    "User update failed: "
                            + user.getUserId()
            );
        }

        return result;
    }

    @Override
    public boolean deactivateUser(int userId) {

        logger.info(
                "Deactivate user request for userId: "
                        + userId
        );

        boolean result = userDAO.deactivateUser(userId);

        if (result) {
            logger.info(
                    "User deactivated successfully: "
                            + userId
            );
        } else {
            logger.warning(
                    "User deactivation failed: "
                            + userId
            );
        }

        return result;
    }
}