package com.instagram.service;

import com.instagram.dao.UserDAO;
import com.instagram.dao.UserDAOImpl;
import com.instagram.model.User;
import com.instagram.util.PasswordUtil;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO = new UserDAOImpl();

    public UserServiceImpl() {
    }

    @Override
    public boolean registerUser(User user) {
        if (userDAO.getUserByUsername(user.getUsername()) != null) {
            return false;   // username taken
        }
        if (userDAO.getUserByEmail(user.getEmail()) != null) {
            return false;   // email taken
        }
        user.setPasswordHash(PasswordUtil.hash(user.getPasswordHash()));  // caller puts plain password here
        if (user.getStatus() == null) user.setStatus("ACTIVE");
        if (user.getRole() == null) user.setRole("USER");
        return userDAO.addUser(user);
    }

    @Override
    public User login(String username, String password) {
        User user = userDAO.getUserByUsername(username);
        if (user == null) {
            return null;
        }
        if (!PasswordUtil.verify(password, user.getPasswordHash())) {
            return null;
        }
        if (!"ACTIVE".equals(user.getStatus())) {
            return null;   // inactive account cannot log in
        }
        return user;
    }

    @Override
    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    @Override
    public boolean deactivateUser(int userId) {
        return userDAO.deactivateUser(userId);
    }
}