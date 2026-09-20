package com.instagram.service;

import com.instagram.model.Follow;
import java.util.List;

public class FollowServiceImpl implements FollowService {

    @Override
    public boolean followUser(Follow follow) {
        // TODO: Validate self-follow and duplicate follow
        // TODO: Call FollowDAO
        return false;
    }

    @Override
    public boolean unfollowUser(int followerId, int followingId) {
        // TODO: Call FollowDAO
        return false;
    }

    @Override
    public boolean isFollowing(int followerId, int followingId) {
        // TODO: Call FollowDAO
        return false;
    }

    @Override
    public List<Follow> getFollowers(int userId) {
        // TODO: Call FollowDAO
        return null;
    }

    @Override
    public List<Follow> getFollowing(int userId) {
        // TODO: Call FollowDAO
        return null;
    }

    @Override
    public int getFollowerCount(int userId) {
        // TODO: Call FollowDAO
        return 0;
    }

    @Override
    public int getFollowingCount(int userId) {
        // TODO: Call FollowDAO
        return 0;
    }
}