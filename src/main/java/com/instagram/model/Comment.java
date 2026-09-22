package com.instagram.model;

import java.time.LocalDateTime;

public class Comment {

    private int commentId;
    private int userId;
    private int postId;
    private Integer parentCommentId;
    private String commentText;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // No-argument constructor
    public Comment() {
    }

    // Parameterized constructor
    public Comment(int commentId, int userId, int postId,
                   Integer parentCommentId, String commentText,
                   LocalDateTime createdAt, LocalDateTime updatedAt) {

        this.commentId = commentId;
        this.userId = userId;
        this.postId = postId;
        this.parentCommentId = parentCommentId;
        this.commentText = commentText;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}