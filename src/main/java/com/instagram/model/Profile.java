package com.instagram.model;

import java.time.LocalDateTime;

public class Profile {

    private int profileId;
    private int userId;
    private String fullName;
    private String bio;
    private String phone;
    private String profileImageUrl;
    private LocalDateTime updatedAt;

    // No-argument constructor
    public Profile() {
    }

    // Parameterized constructor
    public Profile(int profileId, int userId, String fullName, String bio,
                   String phone, String profileImageUrl,
                   LocalDateTime updatedAt) {

        this.profileId = profileId;
        this.userId = userId;
        this.fullName = fullName;
        this.bio = bio;
        this.phone = phone;
        this.profileImageUrl = profileImageUrl;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}