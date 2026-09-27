package com.instagram.dao;

import com.instagram.model.Like;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class LikeDAOImpl implements LikeDAO {

    private static final Logger logger =
            Logger.getLogger(LikeDAOImpl.class.getName());

    private static final String URL =
            "jdbc:mysql://localhost:3306/instagram_db";

    private static final String USER =
            "root";

    private static final String PASSWORD =
            "Mysql@123";

    @Override
    public boolean addLike(Like like) {

        String sql =
                "INSERT INTO likes (user_id, post_id) " +
                        "VALUES (?, ?)";

        try (Connection connection =
                     DriverManager.getConnection(
                             URL,
                             USER,
                             PASSWORD);

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(
                    1,
                    like.getUserId()
            );

            statement.setInt(
                    2,
                    like.getPostId()
            );

            int rows =
                    statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            logger.severe(
                    "Error adding like: "
                            + e.getMessage()
            );

            return false;
        }
    }

    @Override
    public boolean removeLike(
            int userId,
            int postId) {

        String sql =
                "DELETE FROM likes " +
                        "WHERE user_id = ? " +
                        "AND post_id = ?";

        try (Connection connection =
                     DriverManager.getConnection(
                             URL,
                             USER,
                             PASSWORD);

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setInt(2, postId);

            int rows =
                    statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            logger.severe(
                    "Error removing like: "
                            + e.getMessage()
            );

            return false;
        }
    }

    @Override
    public boolean hasUserLikedPost(
            int userId,
            int postId) {

        String sql =
                "SELECT COUNT(*) " +
                        "FROM likes " +
                        "WHERE user_id = ? " +
                        "AND post_id = ?";

        try (Connection connection =
                     DriverManager.getConnection(
                             URL,
                             USER,
                             PASSWORD);

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setInt(2, postId);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                return resultSet.getInt(1) > 0;
            }

        } catch (SQLException e) {

            logger.severe(
                    "Error checking like: "
                            + e.getMessage()
            );
        }

        return false;
    }

    @Override
    public int getLikeCountByPostId(
            int postId) {

        String sql =
                "SELECT COUNT(*) " +
                        "FROM likes " +
                        "WHERE post_id = ?";

        try (Connection connection =
                     DriverManager.getConnection(
                             URL,
                             USER,
                             PASSWORD);

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, postId);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                return resultSet.getInt(1);
            }

        } catch (SQLException e) {

            logger.severe(
                    "Error getting like count: "
                            + e.getMessage()
            );
        }

        return 0;
    }

    @Override
    public List<Like> getLikesByPostId(
            int postId) {

        List<Like> likes =
                new ArrayList<>();

        String sql =
                "SELECT like_id, user_id, post_id, created_at " +
                        "FROM likes " +
                        "WHERE post_id = ? " +
                        "ORDER BY created_at";

        try (Connection connection =
                     DriverManager.getConnection(
                             URL,
                             USER,
                             PASSWORD);

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, postId);

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                Like like =
                        new Like();

                like.setLikeId(
                        resultSet.getInt("like_id")
                );

                like.setUserId(
                        resultSet.getInt("user_id")
                );

                like.setPostId(
                        resultSet.getInt("post_id")
                );

                Timestamp timestamp =
                        resultSet.getTimestamp(
                                "created_at"
                        );

                if (timestamp != null) {

                    like.setCreatedAt(
                            timestamp.toLocalDateTime()
                    );
                }

                likes.add(like);
            }

        } catch (SQLException e) {

            logger.severe(
                    "Error getting likes by post: "
                            + e.getMessage()
            );
        }

        return likes;
    }

    @Override
    public List<Like> getLikesByUserId(
            int userId) {

        List<Like> likes =
                new ArrayList<>();

        String sql =
                "SELECT like_id, user_id, post_id, created_at " +
                        "FROM likes " +
                        "WHERE user_id = ? " +
                        "ORDER BY created_at";

        try (Connection connection =
                     DriverManager.getConnection(
                             URL,
                             USER,
                             PASSWORD);

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                Like like =
                        new Like();

                like.setLikeId(
                        resultSet.getInt("like_id")
                );

                like.setUserId(
                        resultSet.getInt("user_id")
                );

                like.setPostId(
                        resultSet.getInt("post_id")
                );

                Timestamp timestamp =
                        resultSet.getTimestamp(
                                "created_at"
                        );

                if (timestamp != null) {

                    like.setCreatedAt(
                            timestamp.toLocalDateTime()
                    );
                }

                likes.add(like);
            }

        } catch (SQLException e) {

            logger.severe(
                    "Error getting likes by user: "
                            + e.getMessage()
            );
        }

        return likes;
    }
}