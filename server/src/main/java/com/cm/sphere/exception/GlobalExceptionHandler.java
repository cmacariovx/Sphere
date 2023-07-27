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
        final ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BaseCustomAuthException.class)
    public ResponseEntity<ApiError> handleBaseCustomAuthException(BaseCustomAuthException ex) {
        logger.error(ex.getMessage(), ex);
        final ApiError apiError = new ApiError(ex.getHttpStatus(), ex.getMessage());
        return new ResponseEntity<>(apiError, ex.getHttpStatus());
    }

    @ExceptionHandler(ContentTypeException.class)
    public ResponseEntity<ApiError> handleContentTypeException(ContentTypeException ex) {
        logger.error(ex.getMessage(), ex);
        final ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiError> handleBadCredentialsException(BadCredentialsException ex) {
        logger.error(ex.getMessage(), ex);
        final ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception ex) {
        final String error = "An unexpected error occurred, please try again.";
        logger.error(error, ex);
        final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, error);
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
