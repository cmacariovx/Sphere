package com.cm.sphere.model.User;

public class BasicUserData {
    private final String firstName;
    private final String lastName;
    private final String about;
    private final String mainInterest;
    private final String profilePictureUrl;
    private final String bannerPictureUrl;
    private final boolean validated;
    private final boolean verified;

    public BasicUserData(String firstName, String lastName, String about, String mainInterest, String profilePictureUrl, String bannerPictureUrl, boolean validated, boolean verified) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.about = about;
        this.mainInterest = mainInterest;
        this.profilePictureUrl = profilePictureUrl;
        this.bannerPictureUrl = bannerPictureUrl;
        this.validated = validated;
        this.verified = verified;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getAbout() {
        return this.about;
    }

    public String getMainInterest() {
        return this.mainInterest;
    }

    public String getProfilePictureUrl() {
        return this.profilePictureUrl;
    }

    public String getBannerPictureUrl() {
        return this.bannerPictureUrl;
    }

    public boolean isValidated() {
        return this.validated;
    }

    public boolean isVerified() {
        return this.verified;
    }
}
