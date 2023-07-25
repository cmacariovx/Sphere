package com.cm.sphere.exception;

import org.springframework.http.HttpStatus;

public class ExpiredAccessTokenException extends BaseCustomAuthException {
    public ExpiredAccessTokenException() {
        super("Access token has expired", HttpStatus.UNAUTHORIZED);
    }
}
