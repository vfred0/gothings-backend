package com.gothings.services.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(MessageException messageException) {
        super(messageException.getMessage());
    }
}
