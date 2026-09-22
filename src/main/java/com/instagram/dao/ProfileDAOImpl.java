package com.instagram.dao;

import com.instagram.model.Profile;

public class ProfileDAOImpl implements ProfileDAO {

    @Override
    public boolean addProfile(Profile profile) {
        // TODO: Implement INSERT query
        return false;
    }

    @Override
    public Profile getProfileById(int profileId) {
        // TODO: Implement SELECT query by profile ID
        return null;
    }

    @Override
    public Profile getProfileByUserId(int userId) {
        // TODO: Implement SELECT query by user ID
        return null;
    }

    @Override
    public boolean updateProfile(Profile profile) {
        // TODO: Implement UPDATE query
        return false;
    }

    @Override
    public boolean deleteProfile(int profileId) {
        // TODO: Implement DELETE query
        return false;
    }
}