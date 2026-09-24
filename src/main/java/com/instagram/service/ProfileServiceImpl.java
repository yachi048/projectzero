package com.instagram.service;

import com.instagram.dao.ProfileDAO;
import com.instagram.dao.ProfileDAOImpl;
import com.instagram.model.Profile;
import com.instagram.util.ProfileValidator;

import java.util.logging.Logger;

public class ProfileServiceImpl implements ProfileService {

    // LOGGER - CLASS LEVEL
    private static final Logger logger =
            Logger.getLogger(ProfileServiceImpl.class.getName());

    private ProfileDAO profileDAO;

    public ProfileServiceImpl() {

        this.profileDAO = new ProfileDAOImpl();

        logger.info("ProfileServiceImpl initialized");
    }

    public ProfileServiceImpl(ProfileDAO profileDAO) {

        this.profileDAO = profileDAO;

        logger.info("ProfileServiceImpl initialized with DAO");
    }

    @Override
    public boolean createProfile(Profile profile) {

        logger.info("Create profile request received");

        if (profile == null) {

            logger.warning("Profile is null");

            return false;
        }

        ProfileValidator.validateUserId(
                profile.getUserId()
        );

        ProfileValidator.validateFullName(
                profile.getFullName()
        );

        ProfileValidator.validateBio(
                profile.getBio()
        );

        ProfileValidator.validatePhone(
                profile.getPhone()
        );

        ProfileValidator.validateProfileImage(
                profile.getProfileImage()
        );

        Profile existingProfile =
                profileDAO.getProfileByUserId(
                        profile.getUserId()
                );

        if (existingProfile != null) {

            logger.warning(
                    "Profile already exists for userId: "
                            + profile.getUserId()
            );

            return false;
        }

        boolean result =
                profileDAO.addProfile(profile);

        if (result) {

            logger.info(
                    "Profile created successfully for userId: "
                            + profile.getUserId()
            );

        } else {

            logger.severe(
                    "Profile creation failed for userId: "
                            + profile.getUserId()
            );
        }

        return result;
    }

    @Override
    public Profile getProfileByUserId(int userId) {

        logger.info(
                "Getting profile for userId: " + userId
        );

        ProfileValidator.validateUserId(userId);

        Profile profile =
                profileDAO.getProfileByUserId(userId);

        if (profile != null) {

            logger.info(
                    "Profile retrieved successfully for userId: "
                            + userId
            );

        } else {

            logger.warning(
                    "Profile not found for userId: " + userId
            );
        }

        return profile;
    }

    @Override
    public boolean updateProfile(Profile profile) {

        logger.info("Update profile request received");

        if (profile == null) {

            logger.warning("Profile is null");

            return false;
        }

        ProfileValidator.validateUserId(
                profile.getUserId()
        );

        ProfileValidator.validateFullName(
                profile.getFullName()
        );

        ProfileValidator.validateBio(
                profile.getBio()
        );

        ProfileValidator.validatePhone(
                profile.getPhone()
        );

        ProfileValidator.validateProfileImage(
                profile.getProfileImage()
        );

        boolean result =
                profileDAO.updateProfile(profile);

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
    }

    @Override
    public boolean deleteProfile(int profileId) {

        logger.info(
                "Delete profile request for profileId: "
                        + profileId
        );

        if (profileId <= 0) {

            logger.warning(
                    "Invalid profileId: " + profileId
            );

            throw new IllegalArgumentException(
                    "Profile ID must be greater than 0"
            );
        }

        boolean result =
                profileDAO.deleteProfile(profileId);

        if (result) {

            logger.info(
                    "Profile deleted successfully: "
                            + profileId
            );

        } else {

            logger.warning(
                    "Profile deletion failed: "
                            + profileId
            );
        }

        return result;
    }
}