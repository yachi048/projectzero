package com.instagram.service;

import com.instagram.model.Like;

import java.util.List;

public interface LikeService {

    boolean likePost(Like like);

    boolean unlikePost(int userId, int postId);

    boolean hasUserLikedPost(
            int userId,
            int postId
    );

    int getLikeCount(int postId);

    List<Like> getLikesByPostId(int postId);

    List<Like> getLikesByUserId(int userId);
}