package org.closure.app.UserModule.exceptions;

public class UserErrorException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UserErrorException(String message) {
        super(message);
    }
}
