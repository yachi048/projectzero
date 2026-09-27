package com.instagram.service;

import com.instagram.dao.CommentDAO;
import com.instagram.dao.CommentDAOImpl;
import com.instagram.exception.CommentException;
import com.instagram.model.Comment;

import java.util.List;
import java.util.logging.Logger;

public class CommentServiceImpl implements CommentService {

    private static final Logger logger =
            Logger.getLogger(CommentServiceImpl.class.getName());

    private final CommentDAO commentDAO;

    public CommentServiceImpl() {
        this.commentDAO = new CommentDAOImpl();
        logger.info("CommentServiceImpl initialized");
    }

    public CommentServiceImpl(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
        logger.info("CommentServiceImpl initialized with DAO");
    }

    @Override
    public boolean addComment(Comment comment) {

        logger.info("Add comment request received");

        if (comment == null) {
            throw new CommentException("Comment cannot be null");
        }

        validateUserId(comment.getUserId());
        validatePostId(comment.getPostId());

        if (comment.getCommentText() == null || comment.getCommentText().trim().isEmpty()) {
            logger.warning("Comment text is empty");
            throw new CommentException("Comment text is required");
        }

        if (comment.getCommentText().length() > 500) {
            throw new CommentException("Comment must be at most 500 characters");
        }

        // If this is a reply, make sure the parent comment actually exists and belongs to the same post
        if (comment.getParentCommentId() != null) {
            Comment parent = commentDAO.getCommentById(comment.getParentCommentId());
            if (parent == null) {
                logger.warning("Parent comment not found: " + comment.getParentCommentId());
                throw new CommentException("The comment you are replying to was not found");
            }
            if (parent.getPostId() != comment.getPostId()) {
                throw new CommentException("Reply must be on the same post as the parent comment");
            }
        }

        boolean result = commentDAO.addComment(comment);

        if (result) {
            logger.info("Comment added successfully with id: " + comment.getCommentId());
        } else {
            logger.warning("Comment creation failed");
        }

        return result;
    }

    @Override
    public Comment getCommentById(int commentId) {
        validateCommentId(commentId);
        return commentDAO.getCommentById(commentId);
    }

    @Override
    public List<Comment> getCommentsByPostId(int postId) {
        validatePostId(postId);
        return commentDAO.getCommentsByPostId(postId);
    }

    @Override
    public List<Comment> getCommentsByUserId(int userId) {
        validateUserId(userId);
        return commentDAO.getCommentsByUserId(userId);
    }

    @Override
    public boolean updateOwnComment(Comment comment) {

        logger.info("Update own comment request for commentId: "
                + (comment != null ? comment.getCommentId() : "null"));

        if (comment == null) {
            throw new CommentException("Comment cannot be null");
        }

        validateCommentId(comment.getCommentId());
        validateUserId(comment.getUserId());

        if (comment.getCommentText() == null || comment.getCommentText().trim().isEmpty()) {
            throw new CommentException("Comment text is required");
        }

        boolean result = commentDAO.updateComment(comment);

        if (result) {
            logger.info("Comment updated successfully: " + comment.getCommentId());
        } else {
            logger.warning("Comment update failed or user is not owner: " + comment.getCommentId());
        }

        return result;
    }

    @Override
    public boolean deleteOwnComment(int commentId, int userId) {

        logger.info("Delete own comment request for commentId: " + commentId + ", userId: " + userId);

        validateCommentId(commentId);
        validateUserId(userId);

        boolean result = commentDAO.deleteComment(commentId, userId);

        if (result) {
            logger.info("Comment deleted successfully: " + commentId);
        } else {
            logger.warning("Comment deletion failed or user is not owner: " + commentId);
        }

        return result;
    }

    @Override
    public int getCommentCount(int postId) {
        validatePostId(postId);
        return commentDAO.countByPost(postId);
    }

    private void validateUserId(int userId) {
        if (userId <= 0) {
            throw new CommentException("User ID must be greater than 0");
        }
    }

    private void validatePostId(int postId) {
        if (postId <= 0) {
            throw new CommentException("Post ID must be greater than 0");
        }
    }

    private void validateCommentId(int commentId) {
        if (commentId <= 0) {
            throw new CommentException("Comment ID must be greater than 0");
        }
    }
}