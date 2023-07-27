package com.cm.sphere.model.response;

import com.cm.sphere.model.user.BasicUserData;

public class AuthResponse {
    private final String message;
    private final String accessToken;
    private final BasicUserData userData;
    private String refreshToken;

    public AuthResponse(String message, String accessToken, BasicUserData userData, String refreshToken) {
        this.message = message;
        this.accessToken = accessToken;
        this.userData = userData;
        this.refreshToken = refreshToken;
    }

    public String getMessage() {
        return this.message;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public BasicUserData getUserData() {
        return this.userData;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
