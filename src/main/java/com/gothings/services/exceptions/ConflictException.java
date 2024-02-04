package com.gothings.services.exceptions;

public class ConflictException extends RuntimeException {
    private static final String DESCRIPTION = "Conflict Exception";

    public ConflictException(MessageException messageException) {
        super(DESCRIPTION + ". " + messageException);
    }
}
