package com.cm.sphere.exception;

import org.springframework.http.HttpStatus;

public class ExpiredRefreshTokenException extends BaseCustomAuthException {
    public ExpiredRefreshTokenException() {
        super("Refresh token has expired", HttpStatus.UNAUTHORIZED);
    }
}
