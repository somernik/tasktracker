package com.sarah.persistence;

/**
 * Error Exception Class
 * Created by Sarah Omernik on 4/18/2017.
 */
public class ErrorException extends Exception {

    private String errorMessage;

    /**
     * Empty constructor.
     */
    public ErrorException() {
    }

    /**
     * Constructor with error Message
     * @param errorMessage the message from the thrown error.
     */
    public ErrorException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Getter for error message
     * @return the error message.
     */
    public String getMessage() {
        return errorMessage;
    }
}
