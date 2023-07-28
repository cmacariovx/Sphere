package com.cm.sphere.model.user;

public class BasicUserData {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String title;
    private final String mainInterest;
    private final String profilePictureUrl;
    private final String bannerPictureUrl;
    private final boolean validated;
    private final boolean verified;

    public BasicUserData(String id, String firstName, String lastName, String title, String mainInterest, String profilePictureUrl, String bannerPictureUrl, boolean validated, boolean verified) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.mainInterest = mainInterest;
        this.profilePictureUrl = profilePictureUrl;
        this.bannerPictureUrl = bannerPictureUrl;
        this.validated = validated;
        this.verified = verified;
    }

    public String getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getTitle() {
        return this.title;
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
