package com.instagram.controller;

import com.instagram.model.Profile;
import com.instagram.service.ProfileService;

public class ProfileController {

    private ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    public boolean createProfile(Profile profile) {
        // TODO: Call ProfileService
        return false;
    }

    public Profile getProfileById(int profileId) {
        // TODO: Call ProfileService
        return null;
    }

    public Profile getProfileByUserId(int userId) {
        // TODO: Call ProfileService
        return null;
    }

    public boolean updateProfile(Profile profile) {
        // TODO: Call ProfileService
        return false;
    }

    public boolean deleteProfile(int profileId) {
        // TODO: Call ProfileService
        return false;
    }
}