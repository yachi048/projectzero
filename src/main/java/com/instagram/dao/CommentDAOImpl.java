package com.instagram.dao;

import com.instagram.model.Comment;

import java.util.List;

public class CommentDAOImpl implements CommentDAO {

    @Override
    public boolean addComment(Comment comment) {
        // TODO: Implement INSERT query
        return false;
    }

    @Override
    public Comment getCommentById(int commentId) {
        // TODO: Implement SELECT query by comment ID
        return null;
    }

    @Override
    public List<Comment> getCommentsByPostId(int postId) {
        // TODO: Implement SELECT query by post ID
        return null;
    }

    @Override
    public List<Comment> getCommentsByUserId(int userId) {
        // TODO: Implement SELECT query by user ID
        return null;
    }

    @Override
    public boolean updateComment(Comment comment) {
        // TODO: Implement UPDATE query
        return false;
    }

    @Override
    public boolean deleteComment(int commentId) {
        // TODO: Implement DELETE query
        return false;
    }
}