package org.closure.app.postModule.exceptions;

public class PostErrorException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public PostErrorException(String message)
    {
        super(message);
    }
}
