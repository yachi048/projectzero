package com.instagram.controller;

import com.instagram.model.Follow;
import com.instagram.service.FollowService;

import java.util.List;

public class FollowController {

    private FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    public boolean followUser(Follow follow) {
        // TODO: Call FollowService
        return false;
    }

    public boolean unfollowUser(int followerId, int followingId) {
        // TODO: Call FollowService
        return false;
    }

    public boolean isFollowing(int followerId, int followingId) {
        // TODO: Call FollowService
        return false;
    }

    public List<Follow> getFollowers(int userId) {
        // TODO: Call FollowService
        return null;
    }

    public List<Follow> getFollowing(int userId) {
        // TODO: Call FollowService
        return null;
    }

    public int getFollowerCount(int userId) {
        // TODO: Call FollowService
        return 0;
    }

    public int getFollowingCount(int userId) {
        // TODO: Call FollowService
        return 0;
    }
}