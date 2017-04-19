package com.sarah.persistence;

import com.sarah.entity.User;
import com.sarah.utility.DatabaseUtility;
import com.sarah.utility.DateUtility;
import org.apache.log4j.Logger;

import java.sql.*;

/**
 * UserData class
 * Handles the user information in the database.
 * Created by Sarah Omernik on 2/8/2017.
 */
public class UserData {

    private final Logger logger = Logger.getLogger(this.getClass());

    /**
     * Creates sql string to add a new user
     * @param firstName users first name
     * @param lastName users last name
     * @param username users username
     * @param email users email
     * @param password users password
     * @return execution of adding a new user
     */
    public boolean addNewUser(String firstName, String lastName, String username, String email, String password) throws ErrorException {
        String sql = "INSERT INTO user (firstName, lastName, username, email, password) VALUES (?, ?, ?, ?, ?)";
        return executeAddUser(sql, lastName, firstName, username, email, password);
    }

    /**
     * Adds new user to database
     * @param sql the sql string to execute
     * @param lastName users last name
     * @param firstName users first name
     * @param username users username
     * @param email users email
     * @param password users password
     * @return boolean if user inserted properly
     */
    private boolean executeAddUser(String sql, String lastName, String firstName, String username, String email,
                                   String password) throws ErrorException {
        logger.info("In executeAddUser");
        logger.error("Demonstrating error level");
        Database database = Database.getInstance();
        Connection connection = null;
        try {
            database.connect();
            connection = database.getConnection();
            PreparedStatement insertStatement = connection.prepareStatement(sql);
            insertStatement.setString(1, firstName);
            insertStatement.setString(2, lastName);
            insertStatement.setString(3, username);
            insertStatement.setString(4, email);
            insertStatement.setString(5, password);
            insertStatement.executeUpdate();
            database.disconnect();
            return true;
        } catch (SQLException e) {
            logger.error("UserData.executeAddUser()...SqlException: ", e);
            throw new ErrorException();

        } catch (Exception e) {
            System.out.println("UserData.executeAddUser()...Exception: " + e);
            e.printStackTrace();
            throw new ErrorException();
        }
    }

    /**
     * Verifies that email exists and password entered matches the password in the database
     * @param email users email
     * @param password user entered password
     * @return executing the query
     */
    public boolean validateUser(String email, String password) {
        String sql = "SELECT COUNT(*) FROM user WHERE email = '" + email + "' AND password = '" + password + "'";
        return executeCheckUser(sql);
    }

    /**
     * Execute the validation query
     * @param sql The sql query to execute
     * @return boolean true if password and username match 1 value
     */
    private boolean executeCheckUser(String sql) {
        ResultSet results;
        Database database = Database.getInstance();
        Connection connection = null;
        int count = 0;

        try {
            database.connect();
            connection = database.getConnection();
            Statement selectStatement = connection.createStatement();
            results = selectStatement.executeQuery(sql);
            count = countResults(results, count);
            database.disconnect();
        } catch (SQLException e) {
            System.out.println("ExecuteQuery TaskEntryData Sql exception: task " + e);
        } catch (Exception e) {
            System.out.println("ExecuteQuery TaskEntryData Exception: task " + e);
            e.printStackTrace();
        }

        return (count == 1);
    }

    /**
     * Analyses the count result
     * @param results the set of results
     * @return the value of the how many results are returned
     * @throws SQLException if there is an error in the sql syntax
     */
    private int countResults(ResultSet results, int count) throws SQLException {
        while (results.next()) {
            count += results.getInt("COUNT(*)");
        }
        return count;
    }

    /**
     * Creates sql query to retrieve 1 user based on email
     * @param userEmail the users email
     * @return User
     */
    public User getUser(Object userEmail) throws ErrorException {
        User user;
        String sql = "SELECT * FROM user WHERE email = '" + userEmail + "'";
        user = executeQuery(sql);
        return user;
    }

    /**
     * Executes a query to get 1 user
     * @param sqlStatement the sql query to execute
     * @return User
     */
    private User executeQuery(String sqlStatement) throws ErrorException {

        User user = new User();
        Database database = Database.getInstance();
        Connection connection = null;
        try {
            database.connect();
            connection = database.getConnection();
            Statement selectStatement = connection.createStatement();
            ResultSet results = selectStatement.executeQuery(sqlStatement);
            user = createUserFromResults(user, results);
            database.disconnect();
        } catch (SQLException e) {
            System.out.println("ExecuteQuery Sql exception: task " + e);
            throw new ErrorException();
        } catch (Exception e) {
            System.out.println("ExecuteQuery Exception: task " + e);
            e.printStackTrace();
            throw new ErrorException();
        }
        return user;
    }

    /**
     * Creates a user from the results
     * @param user a single User
     * @param results the results set
     * @return User
     * @throws SQLException if there is incorrect sql syntax
     */
    private User createUserFromResults(User user, ResultSet results) throws SQLException {
        while (results.next()) {
            user.setUsername(results.getString("username"));
            user.setEmail(results.getString("email"));
            user.setFirstName(results.getString("firstName"));
            user.setLastName(results.getString("lastName"));
            user.setPassword(results.getString("password"));
        }
        return user;
    }

    /**
     * Edits 1 or more values of a user
     * @param username the users username
     * @param email the users email
     * @param firstName the users firstname
     * @param lastName the users lastname
     * @param password the users password
     * @param oldEmail the users original email
     * @return boolean if the execution went successfully
     * @throws Exception a general exception
     */
    public boolean editUser(String username, String email, String firstName, String lastName, String password, String oldEmail) throws ErrorException {

        String sql = "UPDATE user SET username = '" + username + "', email = '" + email + "', firstName = '" + firstName
                + "', lastName = '" + lastName + "', password = '" + password + "' WHERE email = '" + oldEmail + "'";
        return DatabaseUtility.executeUpdate(sql);
    }

}
