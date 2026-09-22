package com.instagram.service;

import com.instagram.model.Like;
import java.util.List;

public class LikeServiceImpl implements LikeService {

    @Override
    public boolean addLike(Like like) {
        // TODO: Add validation and call LikeDAO
        return false;
    }

    @Override
    public boolean removeLike(int userId, int postId) {
        // TODO: Call LikeDAO
        return false;
    }

    @Override
    public boolean hasUserLikedPost(int userId, int postId) {
        // TODO: Call LikeDAO
        return false;
    }

    @Override
    public int getLikeCountByPostId(int postId) {
        // TODO: Call LikeDAO
        return 0;
    }

    @Override
    public List<Like> getLikesByPostId(int postId) {
        // TODO: Call LikeDAO
        return null;
    }

    @Override
    public List<Like> getLikesByUserId(int userId) {
        // TODO: Call LikeDAO
        return null;
    }
}