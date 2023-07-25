package com.cm.sphere.exception;

import org.springframework.http.HttpStatus;

public class InvalidAccessTokenHeaderException extends BaseCustomAuthException {
    public InvalidAccessTokenHeaderException() {
        super("Invalid access token header.", HttpStatus.BAD_REQUEST);
    }
}
