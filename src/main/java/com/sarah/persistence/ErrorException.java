package com.sarah.persistence;

/**
 * Error Exception Class
 * Created by Sarah Omernik on 4/18/2017.
 */
public class ErrorException extends Exception {

    private String errorMessage;

    public ErrorException() {
    }

    public ErrorException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return errorMessage;
    }
}
