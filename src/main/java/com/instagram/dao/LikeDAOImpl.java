package com.instagram.dao;

import com.instagram.model.Like;

import java.util.List;

public class LikeDAOImpl implements LikeDAO {

    @Override
    public boolean addLike(Like like) {
        // TODO: Implement INSERT query
        return false;
    }

    @Override
    public boolean removeLike(int userId, int postId) {
        // TODO: Implement DELETE query
        return false;
    }

    @Override
    public boolean hasUserLikedPost(int userId, int postId) {
        // TODO: Implement SELECT query to check existing like
        return false;
    }

    @Override
    public int getLikeCountByPostId(int postId) {
        // TODO: Implement COUNT query
        return 0;
    }

    @Override
    public List<Like> getLikesByPostId(int postId) {
        // TODO: Implement SELECT query by post ID
        return null;
    }

    @Override
    public List<Like> getLikesByUserId(int userId) {
        // TODO: Implement SELECT query by user ID
        return null;
    }
}