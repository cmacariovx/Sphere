package com.cm.sphere.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cm.sphere.model.error.ApiError;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        logger.error(ex.getMessage(), ex);
        return toResponseEntity(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BaseCustomAuthException.class)
    public ResponseEntity<ApiError> handleBaseCustomAuthException(BaseCustomAuthException ex) {
        logger.error(ex.getMessage(), ex);
        return toResponseEntity(ex, ex.getHttpStatus());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiError> handleBadCredentialsException(BadCredentialsException ex) {
        logger.error(ex.getMessage(), ex);
        return toResponseEntity(ex, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException ex) {
        logger.error(ex.getMessage(), ex);
        return toResponseEntity(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception ex) {
        final String error = "An unexpected error occurred, please try again.";
        logger.error(error, ex);
        return toResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ApiError> toResponseEntity(Exception ex, HttpStatus status) {
        final ApiError apiError = new ApiError(status, ex.getMessage());
        return new ResponseEntity<>(apiError, status);
    }
}
