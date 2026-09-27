package com.instagram.controller;

import com.instagram.model.Comment;
import com.instagram.service.CommentService;

import java.util.List;
import java.util.logging.Logger;

public class CommentController {

    private static final Logger logger =
            Logger.getLogger(CommentController.class.getName());

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
        logger.info("CommentController initialized");
    }

    public boolean addComment(Comment comment) {
        logger.info("Add comment request");
        return commentService.addComment(comment);
    }

    public Comment getCommentById(int commentId) {
        logger.info("Get comment request for commentId: " + commentId);
        return commentService.getCommentById(commentId);
    }

    public List<Comment> getCommentsByPostId(int postId) {
        logger.info("Get comments request for postId: " + postId);
        return commentService.getCommentsByPostId(postId);
    }

    public List<Comment> getCommentsByUserId(int userId) {
        logger.info("Get comments request for userId: " + userId);
        return commentService.getCommentsByUserId(userId);
    }

    public boolean updateOwnComment(Comment comment) {
        logger.info("Update own comment request for commentId: "
                + (comment != null ? comment.getCommentId() : "null"));
        return commentService.updateOwnComment(comment);
    }

    public boolean deleteOwnComment(int commentId, int userId) {
        logger.info("Delete own comment request for commentId: " + commentId);
        return commentService.deleteOwnComment(commentId, userId);
    }

    public int getCommentCount(int postId) {
        logger.info("Get comment count request for postId: " + postId);
        return commentService.getCommentCount(postId);
    }
}