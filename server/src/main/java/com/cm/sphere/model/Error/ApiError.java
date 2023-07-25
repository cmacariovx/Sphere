package com.cm.sphere.model.Error;

import java.time.Instant;

import org.springframework.http.HttpStatus;

public class ApiError {
    private final HttpStatus httpStatus;
    private final String error;
    private final Instant timestamp;

    public ApiError(HttpStatus httpStatus, String error) {
        this.httpStatus = httpStatus;
        this.error = error;
        this.timestamp = Instant.now();
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public String getError() {
        return this.error;
    }

    public Instant getTimestamp() {
        return this.timestamp;
    }
}
