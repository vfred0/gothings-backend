package com.gothings.services.exceptions;

public class NotFoundException extends RuntimeException{
    private static final String DESCRIPTION = "Not Found Exception";

    public NotFoundException(MessageException messageException) {
        super(DESCRIPTION + ". " + messageException);
    }
}
