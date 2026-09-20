package com.instagram.model;

import java.time.LocalDateTime;

public class Like {

    private int likeId;
    private int userId;
    private int postId;
    private LocalDateTime createdAt;

    // No-argument constructor
    public Like() {
    }

    // Parameterized constructor
    public Like(int likeId, int userId, int postId,
                LocalDateTime createdAt) {

        this.likeId = likeId;
        this.userId = userId;
        this.postId = postId;
        this.createdAt = createdAt;
    }

    // Getters and Setters

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}