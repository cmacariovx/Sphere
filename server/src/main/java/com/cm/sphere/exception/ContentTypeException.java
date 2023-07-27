package com.cm.sphere.exception;

public class ContentTypeException extends RuntimeException {
    public ContentTypeException() {
        super("Invalid content-type.");
    }
}
