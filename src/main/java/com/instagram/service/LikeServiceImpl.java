package com.instagram.service;

import com.instagram.dao.LikeDAO;
import com.instagram.dao.LikeDAOImpl;
import com.instagram.exception.LikeException;
import com.instagram.model.Like;

import java.util.List;
import java.util.logging.Logger;

public class LikeServiceImpl implements LikeService {

    private static final Logger logger =
            Logger.getLogger(
                    LikeServiceImpl.class.getName()
            );

    private final LikeDAO likeDAO;

    public LikeServiceImpl() {

        this.likeDAO =
                new LikeDAOImpl();

        logger.info(
                "LikeServiceImpl initialized"
        );
    }

    public LikeServiceImpl(LikeDAO likeDAO) {

        this.likeDAO = likeDAO;

        logger.info(
                "LikeServiceImpl initialized with DAO"
        );
    }

    @Override
    public boolean likePost(Like like) {

        logger.info(
                "Like post request received"
        );

        if (like == null) {

            throw new LikeException(
                    "Like cannot be null"
            );
        }

        validateUserId(
                like.getUserId()
        );

        validatePostId(
                like.getPostId()
        );

        if (likeDAO.hasUserLikedPost(
                like.getUserId(),
                like.getPostId())) {

            throw new LikeException(
                    "User has already liked this post"
            );
        }

        boolean result =
                likeDAO.addLike(like);

        if (!result) {

            throw new LikeException(
                    "Failed to like post"
            );
        }

        logger.info(
                "Post liked successfully"
        );

        return true;
    }

    @Override
    public boolean unlikePost(
            int userId,
            int postId) {

        logger.info(
                "Unlike post request received"
        );

        validateUserId(userId);
        validatePostId(postId);

        if (!likeDAO.hasUserLikedPost(
                userId,
                postId)) {

            throw new LikeException(
                    "User has not liked this post"
            );
        }

        boolean result =
                likeDAO.removeLike(
                        userId,
                        postId
                );

        if (!result) {

            throw new LikeException(
                    "Failed to unlike post"
            );
        }

        logger.info(
                "Post unliked successfully"
        );

        return true;
    }

    @Override
    public boolean hasUserLikedPost(
            int userId,
            int postId) {

        validateUserId(userId);
        validatePostId(postId);

        return likeDAO.hasUserLikedPost(
                userId,
                postId
        );
    }

    @Override
    public int getLikeCount(
            int postId) {

        validatePostId(postId);

        return likeDAO.getLikeCountByPostId(
                postId
        );
    }

    @Override
    public List<Like> getLikesByPostId(
            int postId) {

        validatePostId(postId);

        return likeDAO.getLikesByPostId(
                postId
        );
    }

    @Override
    public List<Like> getLikesByUserId(
            int userId) {

        validateUserId(userId);

        return likeDAO.getLikesByUserId(
                userId
        );
    }

    private void validateUserId(
            int userId) {

        if (userId <= 0) {

            throw new LikeException(
                    "User ID must be greater than 0"
            );
        }
    }

    private void validatePostId(
            int postId) {

        if (postId <= 0) {

            throw new LikeException(
                    "Post ID must be greater than 0"
            );
        }
    }
}