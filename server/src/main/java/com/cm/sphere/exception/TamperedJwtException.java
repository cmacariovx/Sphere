package com.cm.sphere.exception;

import org.springframework.http.HttpStatus;

public class TamperedJwtException extends BaseCustomAuthException {
    public TamperedJwtException() {
        super("JWT token has been tampered with.", HttpStatus.BAD_REQUEST);
    }
}
