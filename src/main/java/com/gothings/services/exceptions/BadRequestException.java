package com.gothings.services.exceptions;

public class BadRequestException extends RuntimeException {
    private static final String DESCRIPTION = "Bad Request Exception";

    public BadRequestException(MessageException messageException) {
        super(DESCRIPTION + ". " + messageException);
    }

}
