package com.instagram.controller;

import com.instagram.model.Comment;
import com.instagram.service.CommentService;

import java.util.List;

public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    public boolean addComment(Comment comment) {
        // TODO: Call CommentService
        return false;
    }

    public Comment getCommentById(int commentId) {
        // TODO: Call CommentService
        return null;
    }

    public List<Comment> getCommentsByPostId(int postId) {
        // TODO: Call CommentService
        return null;
    }

    public List<Comment> getCommentsByUserId(int userId) {
        // TODO: Call CommentService
        return null;
    }

    public boolean updateComment(Comment comment) {
        // TODO: Call CommentService
        return false;
    }

    public boolean deleteComment(int commentId) {
        // TODO: Call CommentService
        return false;
    }
}