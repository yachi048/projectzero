package com.instagram.dao;

import com.instagram.model.User;
import com.instagram.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDAOImpl implements UserDAO {

    private static final Logger logger =
            Logger.getLogger(UserDAOImpl.class.getName());

    @Override
    public boolean addUser(User user) {

        logger.info(
                "Adding user: "
                        + user.getUsername()
        );

        String sql = "INSERT INTO users " +
                "(username, email, password_hash, status, role) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPasswordHash());
            statement.setString(4, user.getStatus());
            statement.setString(5, user.getRole());

            int rows = statement.executeUpdate();

            boolean result = rows > 0;

            if (result) {
                logger.info(
                        "User added successfully: "
                                + user.getUsername()
                );
            } else {
                logger.warning(
                        "User insertion failed: "
                                + user.getUsername()
                );
            }

            return result;

        } catch (SQLException e) {

            logger.severe(
                    "Error adding user: "
                            + e.getMessage()
            );

            return false;
        }
    }

    @Override
    public User getUserById(int userId) {

        logger.info(
                "Fetching user by userId: "
                        + userId
        );

        String sql =
                "SELECT * FROM users WHERE user_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {

                    logger.info(
                            "User found for userId: "
                                    + userId
                    );

                    return mapUser(resultSet);
                }
            }

        } catch (SQLException e) {

            logger.severe(
                    "Error fetching user by ID: "
                            + e.getMessage()
            );
        }

        logger.warning(
                "User not found for userId: "
                        + userId
        );

        return null;
    }

    @Override
    public User getUserByUsername(String username) {

        logger.info(
                "Fetching user by username: "
                        + username
        );

        String sql =
                "SELECT * FROM users WHERE username = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, username);

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {

                    logger.info(
                            "User found for username: "
                                    + username
                    );

                    return mapUser(resultSet);
                }
            }

        } catch (SQLException e) {

            logger.severe(
                    "Error fetching user by username: "
                            + e.getMessage()
            );
        }

        logger.warning(
                "User not found for username: "
                        + username
        );

        return null;
    }

    @Override
    public User getUserByEmail(String email) {

        logger.info(
                "Fetching user by email: "
                        + email
        );

        String sql =
                "SELECT * FROM users WHERE email = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, email);

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {

                    logger.info(
                            "User found for email: "
                                    + email
                    );

                    return mapUser(resultSet);
                }
            }

        } catch (SQLException e) {

            logger.severe(
                    "Error fetching user by email: "
                            + e.getMessage()
            );
        }

        logger.warning(
                "User not found for email: "
                        + email
        );

        return null;
    }

    @Override
    public List<User> getAllUsers() {

        logger.info("Fetching all users");

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM users";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {
                users.add(mapUser(resultSet));
            }

            logger.info(
                    "Total users fetched: "
                            + users.size()
            );

        } catch (SQLException e) {

            logger.severe(
                    "Error fetching all users: "
                            + e.getMessage()
            );
        }

        return users;
    }

    @Override
    public boolean updateUser(User user) {

        logger.info(
                "Updating user: "
                        + user.getUserId()
        );

        String sql =
                "UPDATE users SET username = ?, email = ?, " +
                        "password_hash = ?, status = ?, role = ? " +
                        "WHERE user_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPasswordHash());
            statement.setString(4, user.getStatus());
            statement.setString(5, user.getRole());
            statement.setInt(6, user.getUserId());

            int rows = statement.executeUpdate();

            boolean result = rows > 0;

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

        } catch (SQLException e) {

            logger.severe(
                    "Error updating user: "
                            + e.getMessage()
            );

            return false;
        }
    }

    @Override
    public boolean deactivateUser(int userId) {

        logger.info(
                "Deactivating user: "
                        + userId
        );

        String sql =
                "UPDATE users SET status = ? WHERE user_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, "INACTIVE");
            statement.setInt(2, userId);

            int rows = statement.executeUpdate();

            boolean result = rows > 0;

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

        } catch (SQLException e) {

            logger.severe(
                    "Error deactivating user: "
                            + e.getMessage()
            );

            return false;
        }
    }

    @Override
    public boolean existsByUsernameOrEmail(
            String username,
            String email) {

        logger.info(
                "Checking duplicate username/email"
        );

        String sql =
                "SELECT COUNT(*) FROM users " +
                        "WHERE username = ? OR email = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, email);

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {

                    boolean exists =
                            resultSet.getInt(1) > 0;

                    logger.info(
                            "Duplicate check result: "
                                    + exists
                    );

                    return exists;
                }
            }

        } catch (SQLException e) {

            logger.severe(
                    "Error checking duplicate user: "
                            + e.getMessage()
            );
        }

        return false;
    }

    private User mapUser(ResultSet resultSet)
            throws SQLException {

        User user = new User();

        user.setUserId(
                resultSet.getInt("user_id")
        );

        user.setUsername(
                resultSet.getString("username")
        );

        user.setEmail(
                resultSet.getString("email")
        );

        user.setPasswordHash(
                resultSet.getString("password_hash")
        );

        user.setStatus(
                resultSet.getString("status")
        );

        user.setRole(
                resultSet.getString("role")
        );

        return user;
    }
}