package com.instagram.service;

import com.instagram.dao.ProfileDAO;
import com.instagram.dao.ProfileDAOImpl;
import com.instagram.exception.ProfileException;
import com.instagram.model.Profile;
import com.instagram.util.ProfileValidator;

public class ProfileServiceImpl implements ProfileService {

    private final ProfileDAO profileDAO;

    public ProfileServiceImpl() {
        this.profileDAO = new ProfileDAOImpl();
    }

    public ProfileServiceImpl(ProfileDAO profileDAO) {
        this.profileDAO = profileDAO;
    }

    @Override
    public boolean createProfile(Profile profile) {

        validate(profile);

        if (profileDAO.getProfileByUserId(
                profile.getUserId()) != null) {

            throw new ProfileException(
                    "Profile already exists for this user"
            );
        }

        return profileDAO.addProfile(profile);
    }

    @Override
    public Profile getProfileByUserId(int userId) {

        try {

            ProfileValidator.validateUserId(userId);

        } catch (IllegalArgumentException e) {

            throw new ProfileException(
                    e.getMessage()
            );
        }

        Profile profile =
                profileDAO.getProfileByUserId(userId);

        if (profile == null) {

            throw new ProfileException(
                    "Profile not found for user ID: "
                            + userId
            );
        }

        return profile;
    }

    @Override
    public boolean updateProfile(Profile profile) {

        validate(profile);

        if (profileDAO.getProfileByUserId(
                profile.getUserId()) == null) {

            throw new ProfileException(
                    "Profile does not exist for this user"
            );
        }

        return profileDAO.updateProfile(profile);
    }

    @Override
    public boolean deleteProfile(int userId) {

        try {

            ProfileValidator.validateUserId(userId);

        } catch (IllegalArgumentException e) {

            throw new ProfileException(
                    e.getMessage()
            );
        }

        boolean result =
                profileDAO.deleteProfile(userId);

        if (!result) {

            throw new ProfileException(
                    "Profile deletion failed for user ID: "
                            + userId
            );
        }

        return result;
    }

    private void validate(Profile profile) {

        if (profile == null) {

            throw new ProfileException(
                    "Profile cannot be null"
            );
        }

        try {

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

        } catch (IllegalArgumentException e) {

            throw new ProfileException(
                    e.getMessage()
            );
        }
    }
}