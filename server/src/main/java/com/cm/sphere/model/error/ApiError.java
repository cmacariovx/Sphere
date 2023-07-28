package com.cm.sphere.model.error;

import java.time.Instant;

import org.springframework.http.HttpStatus;

public class ApiError {
    private final HttpStatus httpStatus;
    private final String message;
    private final Instant timestamp;

    public ApiError(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.timestamp = Instant.now();
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public String getError() {
        return this.message;
    }

    public Instant getTimestamp() {
        return this.timestamp;
    }
}
