package com.sarah.entity;

/**
 * User class
 * Created by Sarah Omernik on 2/8/2017.
 * Holds all User information
 */
public class User {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;

    /**
     * Empty constructor for User
     */
    public User() {
    }

    /**
     * Constructor with parameters for User
     * @param username The users username
     * @param email The users email
     * @param firstName The users first name
     * @param lastName The users last name
     * @param password The users password
     */
    public User(String username, String email, String firstName, String lastName, String password) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    /**
     * Getter for username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for username
     * @param username The users username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for email
     * @param email The users email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for firstName
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for firstName
     * @param firstName The users first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for lastName
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for lastName
     * @param lastName The users last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password
     * @param password The users password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
