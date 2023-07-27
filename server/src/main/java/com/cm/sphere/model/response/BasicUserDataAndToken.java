package com.cm.sphere.model.response;

import com.cm.sphere.model.user.BasicUserData;

public class BasicUserDataAndToken {
    private final String newAccessToken;
    private final BasicUserData basicUserData;

    public BasicUserDataAndToken(String newAccessToken, BasicUserData basicUserData) {
        this.newAccessToken = newAccessToken;
        this.basicUserData = basicUserData;
    }

    public String getNewAccessToken() {
        return this.newAccessToken;
    }

    public BasicUserData getBasicUserData() {
        return this.basicUserData;
    }
}
