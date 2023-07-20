package com.cm.sphere.model.User;

import org.springframework.data.mongodb.core.mapping.Field;

public class Assets {
    @Field(name = "profilePictureUrl")
    private String profilePictureUrl;

    @Field(name = "bannerPictureUrl")
    private String bannerPictureUrl;

    public Assets() {
        this.profilePictureUrl = null;
        this.bannerPictureUrl = null;
    }

    public String getProfilePictureUrl() {
        return this.profilePictureUrl;
    }

    public String getBannerPictureUrl() {
        return this.bannerPictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public void setBannerPictureUrl(String bannerPictureUrl) {
        this.bannerPictureUrl = bannerPictureUrl;
    }
}
