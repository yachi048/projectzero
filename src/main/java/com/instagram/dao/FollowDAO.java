package com.instagram.dao;

import com.instagram.model.Follow;

import java.util.List;

public interface FollowDAO {

    boolean addFollow(Follow follow);

    boolean removeFollow(int followerId, int followingId);

    boolean isFollowing(int followerId, int followingId);

    List<Follow> getFollowers(int userId);

    List<Follow> getFollowing(int userId);

    int getFollowerCount(int userId);

    int getFollowingCount(int userId);
}