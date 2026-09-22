package com.instagram.service;

import com.instagram.model.Comment;
import java.util.List;

public class CommentServiceImpl implements CommentService {

    @Override
    public boolean addComment(Comment comment) {
        // TODO: Add validation and call CommentDAO
        return false;
    }

    @Override
    public Comment getCommentById(int commentId) {
        // TODO: Call CommentDAO
        return null;
    }

    @Override
    public List<Comment> getCommentsByPostId(int postId) {
        // TODO: Call CommentDAO
        return null;
    }

    @Override
    public List<Comment> getCommentsByUserId(int userId) {
        // TODO: Call CommentDAO
        return null;
    }

    @Override
    public boolean updateComment(Comment comment) {
        // TODO: Add validation and call CommentDAO
        return false;
    }

    @Override
    public boolean deleteComment(int commentId) {
        // TODO: Call CommentDAO
        return false;
    }
}