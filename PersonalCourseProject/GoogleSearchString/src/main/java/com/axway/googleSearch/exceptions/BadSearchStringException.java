package com.axway.googleSearch.exceptions;

public class BadSearchStringException extends BadSearchException {
    public BadSearchStringException() {
        super.message = "You are trying to search empty string";
    }
}
