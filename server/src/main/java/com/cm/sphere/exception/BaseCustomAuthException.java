package com.cm.sphere.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

public class BaseCustomAuthException extends AuthenticationException {
    private final HttpStatus httpStatus;

    public BaseCustomAuthException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}
