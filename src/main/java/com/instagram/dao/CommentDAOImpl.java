package com.instagram.dao;

import com.instagram.exception.CommentException;
import com.instagram.model.Comment;
import com.instagram.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CommentDAOImpl implements CommentDAO {

    private static final Logger logger =
            Logger.getLogger(CommentDAOImpl.class.getName());

    private static final String COLUMNS =
            "comment_id, user_id, post_id, parent_comment_id, comment_text, created_at, updated_at";

    @Override
    public boolean addComment(Comment comment) {

        logger.info("Adding comment for postId: " + comment.getPostId()
                + " by userId: " + comment.getUserId());

        String sql = "INSERT INTO comments (user_id, post_id, parent_comment_id, comment_text) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, comment.getUserId());
            ps.setInt(2, comment.getPostId());

            if (comment.getParentCommentId() == null) {
                ps.setNull(3, Types.INTEGER);
            } else {
                ps.setInt(3, comment.getParentCommentId());
            }

            ps.setString(4, comment.getCommentText());

            boolean result = ps.executeUpdate() > 0;

            if (result) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        comment.setCommentId(keys.getInt(1));
                    }
                }
                logger.info("Comment added successfully with id: " + comment.getCommentId());
            } else {
                logger.warning("Comment insert affected 0 rows");
            }

            return result;

        } catch (Exception e) {
            logger.severe("Error adding comment: " + e.getMessage());
            throw new CommentException("Failed to add comment", e);
        }
    }

    @Override
    public Comment getCommentById(int commentId) {

        logger.info("Fetching comment for commentId: " + commentId);

        String sql = "SELECT " + COLUMNS + " FROM comments WHERE comment_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, commentId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    logger.info("Comment found for commentId: " + commentId);
                    return mapComment(rs);
                }
            }

            logger.warning("Comment not found for commentId: " + commentId);
            return null;

        } catch (Exception e) {
            logger.severe("Error fetching commentId: " + commentId + " - " + e.getMessage());
            throw new CommentException("Failed to fetch comment", e);
        }
    }

    @Override
    public List<Comment> getCommentsByPostId(int postId) {

        logger.info("Fetching comments for postId: " + postId);

        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT " + COLUMNS + " FROM comments WHERE post_id = ? ORDER BY created_at ASC";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, postId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    comments.add(mapComment(rs));
                }
            }

            logger.info("Comments fetched for postId " + postId + ": " + comments.size());
            return comments;

        } catch (Exception e) {
            logger.severe("Error fetching comments for postId: " + postId + " - " + e.getMessage());
            throw new CommentException("Failed to fetch comments for post", e);
        }
    }

    @Override
    public List<Comment> getCommentsByUserId(int userId) {

        logger.info("Fetching comments for userId: " + userId);

        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT " + COLUMNS + " FROM comments WHERE user_id = ? ORDER BY created_at DESC";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    comments.add(mapComment(rs));
                }
            }

            logger.info("Comments fetched for userId " + userId + ": " + comments.size());
            return comments;

        } catch (Exception e) {
            logger.severe("Error fetching comments for userId: " + userId + " - " + e.getMessage());
            throw new CommentException("Failed to fetch user's comments", e);
        }
    }

    @Override
    public boolean updateComment(Comment comment) {

        logger.info("Updating commentId: " + comment.getCommentId()
                + " for userId: " + comment.getUserId());

        String sql = "UPDATE comments SET comment_text = ? WHERE comment_id = ? AND user_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, comment.getCommentText());
            ps.setInt(2, comment.getCommentId());
            ps.setInt(3, comment.getUserId());

            boolean result = ps.executeUpdate() > 0;

            if (result) {
                logger.info("Comment updated successfully: " + comment.getCommentId());
            } else {
                logger.warning("Comment not found or user is not owner: " + comment.getCommentId());
            }

            return result;

        } catch (Exception e) {
            logger.severe("Error updating commentId: " + comment.getCommentId() + " - " + e.getMessage());
            throw new CommentException("Failed to update comment", e);
        }
    }

    @Override
    public boolean deleteComment(int commentId, int userId) {

        logger.info("Deleting commentId: " + commentId + " for userId: " + userId);

        String sql = "DELETE FROM comments WHERE comment_id = ? AND user_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, commentId);
            ps.setInt(2, userId);

            boolean result = ps.executeUpdate() > 0;

            if (result) {
                logger.info("Comment deleted successfully: " + commentId);
            } else {
                logger.warning("Comment not found or user is not owner: " + commentId);
            }

            return result;

        } catch (Exception e) {
            logger.severe("Error deleting commentId: " + commentId + " - " + e.getMessage());
            throw new CommentException("Failed to delete comment", e);
        }
    }

    @Override
    public int countByPost(int postId) {

        String sql = "SELECT COUNT(*) FROM comments WHERE post_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, postId);

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }

        } catch (Exception e) {
            logger.severe("Error counting comments for postId: " + postId + " - " + e.getMessage());
            throw new CommentException("Failed to count comments", e);
        }
    }

    private Comment mapComment(ResultSet rs) throws Exception {
        Comment comment = new Comment();
        comment.setCommentId(rs.getInt("comment_id"));
        comment.setUserId(rs.getInt("user_id"));
        comment.setPostId(rs.getInt("post_id"));

        int parentId = rs.getInt("parent_comment_id");
        comment.setParentCommentId(rs.wasNull() ? null : parentId);

        comment.setCommentText(rs.getString("comment_text"));

        Timestamp createdAt = rs.getTimestamp("created_at");
        if (createdAt != null) {
            comment.setCreatedAt(createdAt.toLocalDateTime());
        }

        Timestamp updatedAt = rs.getTimestamp("updated_at");
        if (updatedAt != null) {
            comment.setUpdatedAt(updatedAt.toLocalDateTime());
        }

        return comment;
    }
}