package com.instagram.controller;

import com.instagram.model.Follow;
import com.instagram.service.FollowService;

import java.util.List;
import java.util.logging.Logger;

public class FollowController {

    private static final Logger logger =
            Logger.getLogger(FollowController.class.getName());

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
        logger.info("FollowController initialized");
    }

    public boolean followUser(int followerId, int followingId) {
        logger.info("Follow user request");
        return followService.followUser(followerId, followingId);
    }

    public boolean unfollowUser(int followerId, int followingId) {
        logger.info("Unfollow user request");
        return followService.unfollowUser(followerId, followingId);
    }

    public boolean isFollowing(int followerId, int followingId) {
        logger.info("Check follow status request");
        return followService.isFollowing(followerId, followingId);
    }

    public List<Follow> getFollowers(int userId) {
        logger.info("Get followers request for userId: " + userId);
        return followService.getFollowers(userId);
    }

    public List<Follow> getFollowing(int userId) {
        logger.info("Get following request for userId: " + userId);
        return followService.getFollowing(userId);
    }

    public int getFollowerCount(int userId) {
        logger.info("Get follower count request for userId: " + userId);
        return followService.getFollowerCount(userId);
    }

    public int getFollowingCount(int userId) {
        logger.info("Get following count request for userId: " + userId);
        return followService.getFollowingCount(userId);
    }
}