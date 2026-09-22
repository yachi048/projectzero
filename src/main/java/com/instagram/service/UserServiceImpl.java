package com.instagram.service;

import com.instagram.dao.UserDAO;
import com.instagram.dao.UserDAOImpl;
import com.instagram.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public UserServiceImpl() {
        this.userDAO = new UserDAOImpl();
    }

    @Override
    public boolean registerUser(User user) {
        return userDAO.addUser(user);
    }

    @Override
    public User login(String username, String password) {

        User user = userDAO.getUserByUsername(username);

        if (user != null
                && user.getPasswordHash().equals(password)
                && "ACTIVE".equalsIgnoreCase(user.getStatus())) {

            return user;
        }

        return null;
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