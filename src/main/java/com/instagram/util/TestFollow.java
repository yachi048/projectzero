package com.instagram.util;

import com.instagram.controller.FollowController;
import com.instagram.model.Follow;
import com.instagram.service.FollowService;
import com.instagram.service.FollowServiceImpl;

import java.util.List;

public class TestFollow {

    public static void main(String[] args) {

        FollowService followService = new FollowServiceImpl();
        FollowController followController = new FollowController(followService);

        // Use two real existing user_ids from your users table.
        // Change these if your actual test users have different ids.
        int userA = 1;
        int userB = 2;

        // 1. FOLLOW
        boolean followed = followController.followUser(userA, userB);
        System.out.println("1. Follow (A follows B): " + followed);

        // 2. IS FOLLOWING
        boolean isFollowing = followController.isFollowing(userA, userB);
        System.out.println("2. Is Following (expect true): " + isFollowing);

        // 3. SELF-FOLLOW (should fail)
        boolean selfFollow;
        try {
            selfFollow = followController.followUser(userA, userA);
        } catch (Exception e) {
            selfFollow = false;
            System.out.println("   (self-follow threw exception: " + e.getMessage() + ")");
        }
        System.out.println("3. Self Follow (expect false): " + selfFollow);

        // 4. DUPLICATE FOLLOW (should fail)
        boolean duplicateFollow;
        try {
            duplicateFollow = followController.followUser(userA, userB);
        } catch (Exception e) {
            duplicateFollow = false;
            System.out.println("   (duplicate follow threw exception: " + e.getMessage() + ")");
        }
        System.out.println("4. Duplicate Follow (expect false): " + duplicateFollow);

        // 5. FOLLOWER COUNT of B (expect 1)
        int followerCount = followController.getFollowerCount(userB);
        System.out.println("5. Follower Count of B (expect 1): " + followerCount);

        // 6. FOLLOWING COUNT of A (expect 1)
        int followingCount = followController.getFollowingCount(userA);
        System.out.println("6. Following Count of A (expect 1): " + followingCount);

        // 7. FOLLOWERS LIST of B
        List<Follow> followersOfB = followController.getFollowers(userB);
        System.out.println("7. Followers of B: " + followersOfB.size());

        // 8. FOLLOWING LIST of A
        List<Follow> followingOfA = followController.getFollowing(userA);
        System.out.println("8. Following of A: " + followingOfA.size());

        // 9. UNFOLLOW
        boolean unfollowed = followController.unfollowUser(userA, userB);
        System.out.println("9. Unfollow: " + unfollowed);

        // 10. IS FOLLOWING AFTER UNFOLLOW (expect false)
        boolean isFollowingAfter = followController.isFollowing(userA, userB);
        System.out.println("10. Is Following After Unfollow (expect false): " + isFollowingAfter);

        // 11. UNFOLLOW AGAIN (should fail - nothing to unfollow)
        boolean unfollowAgain;
        try {
            unfollowAgain = followController.unfollowUser(userA, userB);
        } catch (Exception e) {
            unfollowAgain = false;
            System.out.println("   (unfollow-again threw exception: " + e.getMessage() + ")");
        }
        System.out.println("11. Unfollow Again (expect false): " + unfollowAgain);

        System.out.println("Follow testing completed.");
    }
}