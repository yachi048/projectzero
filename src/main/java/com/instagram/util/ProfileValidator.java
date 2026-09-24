package com.instagram.util;

public final class ProfileValidator {

    private ProfileValidator() {
    }

    public static void validateUserId(int userId) {

        if (userId <= 0) {
            throw new IllegalArgumentException(
                    "User ID must be greater than 0"
            );
        }
    }

    public static void validateFullName(String fullName) {

        if (fullName == null ||
                fullName.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Full name is required"
            );
        }

        if (fullName.length() > 100) {

            throw new IllegalArgumentException(
                    "Full name must not exceed 100 characters"
            );
        }
    }

    public static void validateBio(String bio) {

        if (bio != null && bio.length() > 255) {

            throw new IllegalArgumentException(
                    "Bio must not exceed 255 characters"
            );
        }
    }

    public static void validatePhone(String phone) {

        if (phone != null
                && !phone.trim().isEmpty()
                && !phone.matches("^[0-9+\\- ]{7,20}$")) {

            throw new IllegalArgumentException(
                    "Phone number is not valid"
            );
        }
    }

    public static void validateProfileImage(
            String profileImage) {

        if (profileImage != null
                && profileImage.length() > 255) {

            throw new IllegalArgumentException(
                    "Profile image must not exceed 255 characters"
            );
        }
    }
}