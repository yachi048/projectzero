package com.instagram.dao;

import com.instagram.model.User;

import java.util.List;
import com.instagram.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean addUser(User user) {

        String sql = "INSERT INTO users " +
                "(username, email, password_hash, status, role) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPasswordHash());
            statement.setString(4, user.getStatus());
            statement.setString(5, user.getRole());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User getUserById(int userId) {
        // TODO: Implement SELECT query by ID
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        // TODO: Implement SELECT query by username
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        // TODO: Implement SELECT query by email
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        // TODO: Implement SELECT ALL query
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        // TODO: Implement UPDATE query
        return false;
    }

    @Override
    public boolean deleteUser(int userId) {
        // TODO: Implement DELETE query
        return false;
    }
}