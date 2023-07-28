package com.cm.sphere.model.request;

import com.cm.sphere.model.validation.HexObjectId;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class FetchProfileDataRequest {
    @NotEmpty
    @NotNull
    @HexObjectId
    private String profileId;

    public FetchProfileDataRequest() {}

    public FetchProfileDataRequest(String profileId) {
        this.profileId = profileId;
    }

    public String getProfileId() {
        return this.profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }
}
