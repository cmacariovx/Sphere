package com.cm.sphere.exception;

import org.springframework.http.HttpStatus;

public class MissingRefreshTokenException extends BaseCustomAuthException {
    public MissingRefreshTokenException() {
        super("No refresh token found.", HttpStatus.BAD_REQUEST);
    }
}
