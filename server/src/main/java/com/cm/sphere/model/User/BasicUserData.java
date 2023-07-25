package com.cm.sphere.model.User;

public class BasicUserData {
    private String firstName;
    private String lastName;
    private String about;
    private String mainInterest;
    private String profilePictureUrl;
    private String bannerPictureUrl;
    private boolean validated;
    private boolean verified;

    public BasicUserData() {}

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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setMainInterest(String mainInterest) {
        this.mainInterest = mainInterest;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public void setBannerPictureUrl(String bannerPictureUrl) {
        this.bannerPictureUrl = bannerPictureUrl;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
