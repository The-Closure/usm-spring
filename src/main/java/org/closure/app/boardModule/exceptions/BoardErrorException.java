package org.closure.app.boardModule.exceptions;

public class BoardErrorException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public BoardErrorException(String message)
    {
        super(message);
    }
}
