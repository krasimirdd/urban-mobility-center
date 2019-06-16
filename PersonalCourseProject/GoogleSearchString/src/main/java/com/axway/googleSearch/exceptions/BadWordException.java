package com.axway.googleSearch.exceptions;

public class BadWordException extends BadSearchException {
    public BadWordException() {
        super.message = "You are trying to search for forbidden string";
    }
}
