package com.instagram.dao;

import com.instagram.exception.FollowException;
import com.instagram.model.Follow;
import com.instagram.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FollowDAOImpl implements FollowDAO {

    private static final Logger logger =
            Logger.getLogger(FollowDAOImpl.class.getName());

    @Override
    public boolean addFollow(Follow follow) {

        logger.info("Adding follow: follower=" + follow.getFollowerId()
                + " following=" + follow.getFollowingId());

        String sql = "INSERT INTO follows (follower_id, following_id) VALUES (?, ?)";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, follow.getFollowerId());
            ps.setInt(2, follow.getFollowingId());

            boolean result = ps.executeUpdate() > 0;

            if (result) {
                logger.info("Follow added successfully");
            } else {
                logger.warning("Follow insert affected 0 rows");
            }

            return result;

        } catch (Exception e) {
            logger.severe("Error adding follow: " + e.getMessage());
            throw new FollowException("Failed to follow user", e);
        }
    }

    @Override
    public boolean removeFollow(int followerId, int followingId) {

        logger.info("Removing follow: follower=" + followerId + " following=" + followingId);

        String sql = "DELETE FROM follows WHERE follower_id = ? AND following_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, followerId);
            ps.setInt(2, followingId);

            boolean result = ps.executeUpdate() > 0;

            if (result) {
                logger.info("Follow removed successfully");
            } else {
                logger.warning("No follow relationship found to remove");
            }

            return result;

        } catch (Exception e) {
            logger.severe("Error removing follow: " + e.getMessage());
            throw new FollowException("Failed to unfollow user", e);
        }
    }

    @Override
    public boolean isFollowing(int followerId, int followingId) {

        logger.info("Checking follow: follower=" + followerId + " following=" + followingId);

        String sql = "SELECT 1 FROM follows WHERE follower_id = ? AND following_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, followerId);
            ps.setInt(2, followingId);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            logger.severe("Error checking follow status: " + e.getMessage());
            throw new FollowException("Failed to check follow status", e);
        }
    }

    @Override
    public List<Follow> getFollowers(int userId) {

        logger.info("Fetching followers for userId: " + userId);

        List<Follow> followers = new ArrayList<>();
        String sql = "SELECT follow_id, follower_id, following_id, created_at " +
                "FROM follows WHERE following_id = ? ORDER BY created_at DESC";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    followers.add(mapFollow(rs));
                }
            }

            logger.info("Followers fetched: " + followers.size());
            return followers;

        } catch (Exception e) {
            logger.severe("Error fetching followers: " + e.getMessage());
            throw new FollowException("Failed to fetch followers", e);
        }
    }

    @Override
    public List<Follow> getFollowing(int userId) {

        logger.info("Fetching following for userId: " + userId);

        List<Follow> following = new ArrayList<>();
        String sql = "SELECT follow_id, follower_id, following_id, created_at " +
                "FROM follows WHERE follower_id = ? ORDER BY created_at DESC";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    following.add(mapFollow(rs));
                }
            }

            logger.info("Following fetched: " + following.size());
            return following;

        } catch (Exception e) {
            logger.severe("Error fetching following: " + e.getMessage());
            throw new FollowException("Failed to fetch following list", e);
        }
    }

    @Override
    public int countFollowers(int userId) {

        String sql = "SELECT COUNT(*) FROM follows WHERE following_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }

        } catch (Exception e) {
            logger.severe("Error counting followers: " + e.getMessage());
            throw new FollowException("Failed to count followers", e);
        }
    }

    @Override
    public int countFollowing(int userId) {

        String sql = "SELECT COUNT(*) FROM follows WHERE follower_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }

        } catch (Exception e) {
            logger.severe("Error counting following: " + e.getMessage());
            throw new FollowException("Failed to count following", e);
        }
    }

    private Follow mapFollow(ResultSet rs) throws Exception {
        Follow follow = new Follow();
        follow.setFollowId(rs.getInt("follow_id"));
        follow.setFollowerId(rs.getInt("follower_id"));
        follow.setFollowingId(rs.getInt("following_id"));

        Timestamp createdAt = rs.getTimestamp("created_at");
        if (createdAt != null) {
            follow.setCreatedAt(createdAt.toLocalDateTime());
        }

        return follow;
    }
}