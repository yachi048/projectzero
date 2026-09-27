package com.instagram.controller;

import com.instagram.model.Like;
import com.instagram.service.LikeService;
import com.instagram.service.LikeServiceImpl;

import java.util.List;
import java.util.logging.Logger;

public class LikeController {

    private static final Logger logger =
            Logger.getLogger(
                    LikeController.class.getName()
            );

    private final LikeService likeService;

    public LikeController() {

        this.likeService =
                new LikeServiceImpl();

        logger.info(
                "LikeController initialized"
        );
    }

    public LikeController(
            LikeService likeService) {

        this.likeService =
                likeService;

        logger.info(
                "LikeController initialized"
        );
    }

    public boolean likePost(
            Like like) {

        logger.info(
                "Like post request"
        );

        return likeService.likePost(
                like
        );
    }

    public boolean unlikePost(
            int userId,
            int postId) {

        logger.info(
                "Unlike post request"
        );

        return likeService.unlikePost(
                userId,
                postId
        );
    }

    public boolean hasUserLikedPost(
            int userId,
            int postId) {

        return likeService.hasUserLikedPost(
                userId,
                postId
        );
    }

    public int getLikeCount(
            int postId) {

        return likeService.getLikeCount(
                postId
        );
    }

    public List<Like> getLikesByPostId(
            int postId) {

        return likeService.getLikesByPostId(
                postId
        );
    }

    public List<Like> getLikesByUserId(
            int userId) {

        return likeService.getLikesByUserId(
                userId
        );
    }
}