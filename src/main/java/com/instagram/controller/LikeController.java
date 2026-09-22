package com.instagram.controller;

import com.instagram.model.Like;
import com.instagram.service.LikeService;

import java.util.List;

public class LikeController {

    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    public boolean addLike(Like like) {
        // TODO: Call LikeService
        return false;
    }

    public boolean removeLike(int userId, int postId) {
        // TODO: Call LikeService
        return false;
    }

    public boolean hasUserLikedPost(int userId, int postId) {
        // TODO: Call LikeService
        return false;
    }

    public int getLikeCountByPostId(int postId) {
        // TODO: Call LikeService
        return 0;
    }

    public List<Like> getLikesByPostId(int postId) {
        // TODO: Call LikeService
        return null;
    }

    public List<Like> getLikesByUserId(int userId) {
        // TODO: Call LikeService
        return null;
    }
}