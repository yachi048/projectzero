package com.instagram.controller;

import com.instagram.model.Profile;
import com.instagram.service.ProfileService;

import java.util.logging.Logger;

public class ProfileController {

    // Logger at CLASS LEVEL
    private static final Logger logger =
            Logger.getLogger(ProfileController.class.getName());

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {

        this.profileService = profileService;

        logger.info("ProfileController initialized");
    }

    public boolean createProfile(Profile profile) {

        logger.info("Create profile request");

        return profileService.createProfile(profile);
    }

    public Profile getProfileByUserId(int userId) {

        logger.info(
                "Get profile request for userId: " + userId
        );

        return profileService.getProfileByUserId(userId);
    }

    public boolean updateProfile(Profile profile) {

        logger.info("Update profile request");

        return profileService.updateProfile(profile);
    }

    public boolean deleteProfile(int profileId) {

        logger.info(
                "Delete profile request for profileId: "
                        + profileId
        );

        return profileService.deleteProfile(profileId);
    }
}