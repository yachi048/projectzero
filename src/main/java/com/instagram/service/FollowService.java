package com.instagram.service;

import com.instagram.model.Follow;
import java.util.List;

public interface FollowService {

    boolean followUser(Follow follow);

    boolean unfollowUser(int followerId, int followingId);

    boolean isFollowing(int followerId, int followingId);

    List<Follow> getFollowers(int userId);

    List<Follow> getFollowing(int userId);

    int getFollowerCount(int userId);

    int getFollowingCount(int userId);
}