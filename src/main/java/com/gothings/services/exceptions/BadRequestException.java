package com.gothings.services.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(MessageException messageException) {
        super(messageException.getMessage());
    }

}
