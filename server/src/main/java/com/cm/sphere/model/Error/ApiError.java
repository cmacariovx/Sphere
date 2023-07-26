package com.cm.sphere.model.Error;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ApiError {
    private final HttpStatus httpStatus;
    private final String message;
    private final Date timestamp;

    public ApiError(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.timestamp = new Date();
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public String getError() {
        return this.message;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }
}
