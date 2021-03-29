package org.closure.app.CommunityModule.exceptions;

public class CommunityErrorException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public CommunityErrorException(String message) 
    { super(message); }
}
