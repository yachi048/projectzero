package com.instagram.service;

import com.instagram.model.Profile;

public class ProfileServiceImpl implements ProfileService {

    @Override
    public boolean createProfile(Profile profile) {
        // TODO: Add validation and call ProfileDAO
        return false;
    }

    @Override
    public Profile getProfileById(int profileId) {
        // TODO: Call ProfileDAO
        return null;
    }

    @Override
    public Profile getProfileByUserId(int userId) {
        // TODO: Call ProfileDAO
        return null;
    }

    @Override
    public boolean updateProfile(Profile profile) {
        // TODO: Add validation and call ProfileDAO
        return false;
    }

    @Override
    public boolean deleteProfile(int profileId) {
        // TODO: Call ProfileDAO
        return false;
    }
}