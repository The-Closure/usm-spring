package org.closure.app.UserModule.exceptions;

public class UserErrorException extends RuntimeException {
    public UserErrorException(String message) {
        super(message);
    }
}
