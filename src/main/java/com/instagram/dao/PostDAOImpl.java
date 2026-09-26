package com.instagram.dao;

import com.instagram.model.Post;
import com.instagram.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PostDAOImpl implements PostDAO {

    private static final Logger logger =
            Logger.getLogger(PostDAOImpl.class.getName());

    @Override
    public boolean addPost(Post post) {

        logger.info("Creating post for userId: " + post.getUserId());

        String sql = "INSERT INTO posts (user_id, caption, image) VALUES (?, ?, ?)";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, post.getUserId());
            ps.setString(2, post.getCaption());
            ps.setBytes(3, post.getImage());

            boolean result = ps.executeUpdate() > 0;

            if (result) {
                logger.info("Post created successfully for userId: " + post.getUserId());
            } else {
                logger.warning("Post creation failed for userId: " + post.getUserId());
            }

            return result;

        } catch (Exception e) {
            logger.severe("Error while creating post for userId: " + post.getUserId() + " - " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Post> getPostsByUserId(int userId) {

        logger.info("Fetching posts for userId: " + userId);

        List<Post> posts = new ArrayList<>();

        String sql = "SELECT post_id, user_id, caption, image, created_at, updated_at " +
                "FROM posts WHERE user_id = ? ORDER BY created_at DESC";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    posts.add(mapPost(rs));
                }
            }

            logger.info("Posts fetched successfully for userId: " + userId);

        } catch (Exception e) {
            logger.severe("Error while fetching posts for userId: " + userId + " - " + e.getMessage());
        }

        return posts;
    }

    @Override
    public List<Post> getOtherUsersPosts(int userId) {

        logger.info("Fetching posts from other users, excluding userId: " + userId);

        List<Post> posts = new ArrayList<>();

        String sql = "SELECT post_id, user_id, caption, image, created_at, updated_at " +
                "FROM posts WHERE user_id <> ? ORDER BY created_at DESC";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    posts.add(mapPost(rs));
                }
            }

            logger.info("Other users' posts fetched successfully");

        } catch (Exception e) {
            logger.severe("Error while fetching other users' posts: " + e.getMessage());
        }

        return posts;
    }

    @Override
    public Post getPostById(int postId) {

        logger.info("Fetching post for postId: " + postId);

        String sql = "SELECT post_id, user_id, caption, image, created_at, updated_at " +
                "FROM posts WHERE post_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, postId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    logger.info("Post found for postId: " + postId);
                    return mapPost(rs);
                }
            }

            logger.warning("Post not found for postId: " + postId);

        } catch (Exception e) {
            logger.severe("Error while fetching postId: " + postId + " - " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean updatePost(Post post) {

        logger.info("Updating postId: " + post.getPostId() + " for userId: " + post.getUserId());

        String sql = "UPDATE posts SET caption = ?, image = ? WHERE post_id = ? AND user_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, post.getCaption());
            ps.setBytes(2, post.getImage());
            ps.setInt(3, post.getPostId());
            ps.setInt(4, post.getUserId());

            boolean result = ps.executeUpdate() > 0;

            if (result) {
                logger.info("Post updated successfully: " + post.getPostId());
            } else {
                logger.warning("Post not found or user is not owner: " + post.getPostId());
            }

            return result;

        } catch (Exception e) {
            logger.severe("Error while updating postId: " + post.getPostId() + " - " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletePost(int postId, int userId) {

        logger.info("Deleting postId: " + postId + " for userId: " + userId);

        String sql = "DELETE FROM posts WHERE post_id = ? AND user_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, postId);
            ps.setInt(2, userId);

            boolean result = ps.executeUpdate() > 0;

            if (result) {
                logger.info("Post deleted successfully: " + postId);
            } else {
                logger.warning("Post not found or user is not owner: " + postId);
            }

            return result;

        } catch (Exception e) {
            logger.severe("Error while deleting postId: " + postId + " - " + e.getMessage());
            return false;
        }
    }

    private Post mapPost(ResultSet rs) throws Exception {

        Post post = new Post();
        post.setPostId(rs.getInt("post_id"));
        post.setUserId(rs.getInt("user_id"));
        post.setCaption(rs.getString("caption"));
        post.setImage(rs.getBytes("image"));

        Timestamp createdAt = rs.getTimestamp("created_at");
        if (createdAt != null) {
            post.setCreatedAt(createdAt.toLocalDateTime());
        }

        Timestamp updatedAt = rs.getTimestamp("updated_at");
        if (updatedAt != null) {
            post.setUpdatedAt(updatedAt.toLocalDateTime());
        }

        return post;
    }
}