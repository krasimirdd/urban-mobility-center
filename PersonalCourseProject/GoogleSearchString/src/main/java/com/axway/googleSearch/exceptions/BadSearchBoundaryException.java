package com.axway.googleSearch.exceptions;

public class BadSearchBoundaryException extends BadSearchException {
    public BadSearchBoundaryException() {
        super.message = "Invalid boundary value. Enter value between 1 and 2 147 483 647";
    }
}
