package com.assignment.exception;

public class TokenException extends Exception{

    private static final long serialVersionUID = 1L;
    String message;

    public TokenException(String message) {
        super(message);
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
