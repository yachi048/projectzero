package com.instagram.service;

import com.instagram.model.Profile;

public interface ProfileService {

    boolean createProfile(Profile profile);

    Profile getProfileById(int profileId);

    Profile getProfileByUserId(int userId);

    boolean updateProfile(Profile profile);

    boolean deleteProfile(int profileId);
}