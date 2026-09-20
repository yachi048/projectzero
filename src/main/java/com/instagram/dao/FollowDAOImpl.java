package com.instagram.dao;

import com.instagram.model.Follow;

import java.util.List;

public class FollowDAOImpl implements FollowDAO {

    @Override
    public boolean addFollow(Follow follow) {
        // TODO: Implement INSERT query
        return false;
    }

    @Override
    public boolean removeFollow(int followerId, int followingId) {
        // TODO: Implement DELETE query
        return false;
    }

    @Override
    public boolean isFollowing(int followerId, int followingId) {
        // TODO: Implement SELECT query to check following
        return false;
    }

    @Override
    public List<Follow> getFollowers(int userId) {
        // TODO: Implement SELECT query for followers
        return null;
    }

    @Override
    public List<Follow> getFollowing(int userId) {
        // TODO: Implement SELECT query for following
        return null;
    }

    @Override
    public int getFollowerCount(int userId) {
        // TODO: Implement COUNT query
        return 0;
    }

    @Override
    public int getFollowingCount(int userId) {
        // TODO: Implement COUNT query
        return 0;
    }
}