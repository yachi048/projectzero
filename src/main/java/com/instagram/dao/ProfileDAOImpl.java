package com.instagram.dao;

import com.instagram.model.Profile;
import com.instagram.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.logging.Logger;

public class ProfileDAOImpl implements ProfileDAO {

    // LOGGER - CLASS LEVEL
    private static final Logger logger =
            Logger.getLogger(ProfileDAOImpl.class.getName());

    @Override
    public boolean addProfile(Profile profile) {

        logger.info(
                "Creating profile for userId: "
                        + profile.getUserId()
        );

        String sql = "INSERT INTO profiles " +
                "(user_id, full_name, bio, phone, profile_image) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps =
                     connection.prepareStatement(sql)) {

            ps.setInt(1, profile.getUserId());
            ps.setString(2, profile.getFullName());
            ps.setString(3, profile.getBio());
            ps.setString(4, profile.getPhone());
            ps.setString(5, profile.getProfileImage());

            boolean result = ps.executeUpdate() > 0;

            if (result) {

                logger.info(
                        "Profile created successfully for userId: "
                                + profile.getUserId()
                );

            } else {

                logger.warning(
                        "Profile creation failed for userId: "
                                + profile.getUserId()
                );
            }

            return result;

        } catch (Exception e) {

            logger.severe(
                    "Error while creating profile for userId: "
                            + profile.getUserId()
                            + " - "
                            + e.getMessage()
            );

            return false;
        }
    }

    @Override
    public Profile getProfileByUserId(int userId) {

        logger.info(
                "Fetching profile for userId: " + userId
        );

        String sql = "SELECT profile_id, user_id, full_name, " +
                "bio, phone, profile_image, updated_at " +
                "FROM profiles WHERE user_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps =
                     connection.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Profile profile = new Profile();

                    profile.setProfileId(
                            rs.getInt("profile_id")
                    );

                    profile.setUserId(
                            rs.getInt("user_id")
                    );

                    profile.setFullName(
                            rs.getString("full_name")
                    );

                    profile.setBio(
                            rs.getString("bio")
                    );

                    profile.setPhone(
                            rs.getString("phone")
                    );

                    profile.setProfileImage(
                            rs.getString("profile_image")
                    );

                    Timestamp timestamp =
                            rs.getTimestamp("updated_at");

                    if (timestamp != null) {

                        profile.setUpdatedAt(
                                timestamp.toLocalDateTime()
                        );
                    }

                    logger.info(
                            "Profile found for userId: " + userId
                    );

                    return profile;
                }

                logger.warning(
                        "Profile not found for userId: " + userId
                );
            }

        } catch (Exception e) {

            logger.severe(
                    "Error while fetching profile for userId: "
                            + userId
                            + " - "
                            + e.getMessage()
            );
        }

        return null;
    }

    @Override
    public boolean updateProfile(Profile profile) {

        logger.info(
                "Updating profile for userId: "
                        + profile.getUserId()
        );

        String sql = "UPDATE profiles SET " +
                "full_name = ?, " +
                "bio = ?, " +
                "phone = ?, " +
                "profile_image = ? " +
                "WHERE user_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps =
                     connection.prepareStatement(sql)) {

            ps.setString(1, profile.getFullName());
            ps.setString(2, profile.getBio());
            ps.setString(3, profile.getPhone());
            ps.setString(4, profile.getProfileImage());
            ps.setInt(5, profile.getUserId());

            boolean result = ps.executeUpdate() > 0;

            if (result) {

                logger.info(
                        "Profile updated successfully for userId: "
                                + profile.getUserId()
                );

            } else {

                logger.warning(
                        "Profile update failed for userId: "
                                + profile.getUserId()
                );
            }

            return result;

        } catch (Exception e) {

            logger.severe(
                    "Error while updating profile for userId: "
                            + profile.getUserId()
                            + " - "
                            + e.getMessage()
            );

            return false;
        }
    }

    @Override
    public boolean deleteProfile(int profileId) {

        logger.info(
                "Deleting profileId: " + profileId
        );

        String sql =
                "DELETE FROM profiles WHERE profile_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps =
                     connection.prepareStatement(sql)) {

            ps.setInt(1, profileId);

            boolean result = ps.executeUpdate() > 0;

            if (result) {

                logger.info(
                        "Profile deleted successfully: "
                                + profileId
                );

            } else {

                logger.warning(
                        "Profile not found: " + profileId
                );
            }

            return result;

        } catch (Exception e) {

            logger.severe(
                    "Error while deleting profileId: "
                            + profileId
                            + " - "
                            + e.getMessage()
            );

            return false;
        }
    }
}