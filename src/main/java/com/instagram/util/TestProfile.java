package com.instagram.util;

import com.instagram.controller.ProfileController;
import com.instagram.model.Profile;
import com.instagram.service.ProfileService;
import com.instagram.service.ProfileServiceImpl;

public class TestProfile {

    public static void main(String[] args) {

        ProfileService profileService =
                new ProfileServiceImpl();

        ProfileController profileController =
                new ProfileController(profileService);

        // 1. CREATE PROFILE

        Profile profile = new Profile();

        profile.setUserId(1);
        profile.setFullName("Chandra Kumar");
        profile.setBio("Java Developer");
        profile.setPhone("9876543210");
        profile.setProfileImage("chandra.jpg");

        boolean created =
                profileController.createProfile(profile);

        System.out.println(
                "1. Create Profile: " + created
        );


        // 2. GET PROFILE

        Profile result =
                profileController.getProfileByUserId(1);

        if (result != null) {

            System.out.println("2. Get Profile: PASS");
            System.out.println(
                    "   Name: " + result.getFullName()
            );
            System.out.println(
                    "   Bio: " + result.getBio()
            );
            System.out.println(
                    "   Phone: " + result.getPhone()
            );

        } else {

            System.out.println("2. Get Profile: FAIL");
        }


        // 3. UPDATE PROFILE

        profile.setFullName("Chandra Updated");
        profile.setBio("Java Full Stack Developer");

        boolean updated =
                profileController.updateProfile(profile);

        System.out.println(
                "3. Update Profile: " + updated
        );


        // 4. DELETE PROFILE

        Profile profileToDelete =
                profileController.getProfileByUserId(1);

        if (profileToDelete != null) {

            boolean deleted =
                    profileController.deleteProfile(
                            profileToDelete.getProfileId()
                    );

            System.out.println(
                    "4. Delete Profile: " + deleted
            );

        } else {

            System.out.println(
                    "4. Delete Profile: No profile found"
            );
        }

        System.out.println(
                "Profile testing completed."
        );
    }
}