package org.closure.app.commentModule.exceptions;

public class CommentErrorException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    public CommentErrorException(String message)
    {
        super(message);
    }
}
