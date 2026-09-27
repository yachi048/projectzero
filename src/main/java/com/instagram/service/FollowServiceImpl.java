package com.instagram.service;

import com.instagram.dao.FollowDAO;
import com.instagram.dao.FollowDAOImpl;
import com.instagram.exception.FollowException;
import com.instagram.model.Follow;

import java.util.List;
import java.util.logging.Logger;

public class FollowServiceImpl implements FollowService {

    private static final Logger logger =
            Logger.getLogger(FollowServiceImpl.class.getName());

    private final FollowDAO followDAO;

    public FollowServiceImpl() {
        this.followDAO = new FollowDAOImpl();
        logger.info("FollowServiceImpl initialized");
    }

    public FollowServiceImpl(FollowDAO followDAO) {
        this.followDAO = followDAO;
        logger.info("FollowServiceImpl initialized with DAO");
    }

    @Override
    public boolean followUser(int followerId, int followingId) {

        logger.info("Follow request: follower=" + followerId + " following=" + followingId);

        validateUserId(followerId);
        validateUserId(followingId);

        if (followerId == followingId) {
            logger.warning("User tried to follow themselves: " + followerId);
            throw new FollowException("You cannot follow yourself");
        }

        if (followDAO.isFollowing(followerId, followingId)) {
            logger.warning("Duplicate follow attempt: " + followerId + " -> " + followingId);
            throw new FollowException("You are already following this user");
        }

        Follow follow = new Follow();
        follow.setFollowerId(followerId);
        follow.setFollowingId(followingId);

        boolean result = followDAO.addFollow(follow);

        if (result) {
            logger.info("User " + followerId + " now follows " + followingId);
        } else {
            logger.warning("Follow failed for follower=" + followerId + " following=" + followingId);
        }

        return result;
    }

    @Override
    public boolean unfollowUser(int followerId, int followingId) {

        logger.info("Unfollow request: follower=" + followerId + " following=" + followingId);

        validateUserId(followerId);
        validateUserId(followingId);

        if (!followDAO.isFollowing(followerId, followingId)) {
            logger.warning("Unfollow attempted but no relationship exists: "
                    + followerId + " -> " + followingId);
            throw new FollowException("You are not following this user");
        }

        boolean result = followDAO.removeFollow(followerId, followingId);

        if (result) {
            logger.info("User " + followerId + " unfollowed " + followingId);
        } else {
            logger.warning("Unfollow failed for follower=" + followerId + " following=" + followingId);
        }

        return result;
    }

    @Override
    public boolean isFollowing(int followerId, int followingId) {
        validateUserId(followerId);
        validateUserId(followingId);
        return followDAO.isFollowing(followerId, followingId);
    }

    @Override
    public List<Follow> getFollowers(int userId) {
        validateUserId(userId);
        return followDAO.getFollowers(userId);
    }

    @Override
    public List<Follow> getFollowing(int userId) {
        validateUserId(userId);
        return followDAO.getFollowing(userId);
    }

    @Override
    public int getFollowerCount(int userId) {
        validateUserId(userId);
        return followDAO.countFollowers(userId);
    }

    @Override
    public int getFollowingCount(int userId) {
        validateUserId(userId);
        return followDAO.countFollowing(userId);
    }

    private void validateUserId(int userId) {
        if (userId <= 0) {
            throw new FollowException("User ID must be greater than 0");
        }
    }
}