package com.sarah.persistence;

/**
 * Error Exception Class
 * Created by Sarah Omernik on 4/18/2017.
 */
public class ErrorException extends Exception {
    public ErrorException() {

    }

    public String showError() {
        return "There was a problem accessing your data.";
    }
}
