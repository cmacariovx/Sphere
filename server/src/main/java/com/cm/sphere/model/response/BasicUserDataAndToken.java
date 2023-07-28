package com.cm.sphere.model.response;

import com.cm.sphere.model.user.BasicUserData;

public class BasicUserDataAndToken {
    private final String newAccessToken;
    private final BasicUserData basicUserData;
    private String newRefreshToken;

    public BasicUserDataAndToken(String newAccessToken, BasicUserData basicUserData, String newRefreshToken) {
        this.newAccessToken = newAccessToken;
        this.basicUserData = basicUserData;
        this.newRefreshToken = newRefreshToken;
    }

    public String getNewAccessToken() {
        return this.newAccessToken;
    }

    public BasicUserData getBasicUserData() {
        return this.basicUserData;
    }

    public String getNewRefreshToken() {
        return this.newRefreshToken;
    }

    public void setNewRefreshToken(String newRefreshToken) {
        this.newRefreshToken = newRefreshToken;
    }
}
