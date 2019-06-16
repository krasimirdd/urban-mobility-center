package com.axway.googleSearch.exceptions;

public class BadSearchException extends Throwable{
    protected String message;

    @Override
    public String getMessage() {
        return message;
    }
}
