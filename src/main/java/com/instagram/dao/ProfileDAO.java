package com.instagram.dao;

import com.instagram.model.Profile;

public interface ProfileDAO {

    boolean addProfile(Profile profile);

    Profile getProfileById(int profileId);

    Profile getProfileByUserId(int userId);

    boolean updateProfile(Profile profile);

    boolean deleteProfile(int profileId);
}