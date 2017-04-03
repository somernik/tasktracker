package com.sarah.persistence;

import com.sarah.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarah Omernik on 2/8/2017.
 */
public class UserData {

    private final Logger logger = Logger.getLogger(this.getClass());

    // Create user
    public boolean addNewUser(String firstName, String lastName, String username, String email, String password) {
        String sql = "INSERT INTO user (firstName, lastName, username, email, password) VALUES (?, ?, ?, ?, ?)";
        return executeAddUser(sql, lastName, firstName, username, email, password);
    }

    public boolean executeAddUser(String sql, String lastName, String firstName, String username, String email, String password) {
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
            return false;

        } catch (Exception e) {
            System.out.println("UserData.executeAddUser()...Exception: " + e);
            e.printStackTrace();
            return false;
        }
    }

    // Check that username exists and password matches
    public boolean validateUser(String email, String password) {
        String sql = "SELECT COUNT(*) FROM user WHERE email = '" + email + "' AND password = '" + password + "'";
        return executeCheckUser(sql);
    }

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
            count = countResults(results);
            database.disconnect();
        } catch (SQLException e) {
            System.out.println("ExecuteQuery TaskEntryData Sql exception: task " + e);
        } catch (Exception e) {
            System.out.println("ExecuteQuery TaskEntryData Exception: task " + e);
            e.printStackTrace();
        }

        if (count != 1) {
            return false;
        } else {
            return true;
        }
    }

    private int countResults(ResultSet results) throws SQLException {
        int count = 0;
        while (results.next()) {
            count = results.getInt("COUNT(*)");
        }
        return count;
    }

    public User getUser(Object userEmail) {
        User user;
        String sql = "SELECT * FROM user WHERE email = '" + userEmail + "'";
        user = executeQuery(sql);
        return user;
    }


    private User executeQuery(String sqlStatement) {

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
        } catch (Exception e) {
            System.out.println("ExecuteQuery Exception: task " + e);
            e.printStackTrace();
        }
        return user;
    }

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

    // Update a task
    public boolean editUser(String username, String email, String firstName, String lastName, String password, String oldEmail) throws Exception {

        TaskEntryData taskEntryData = new TaskEntryData();
        String sql = "UPDATE user SET username = '" + username + "', email = '" + email + "', firstName = '" + firstName
                + "', lastName = '" + lastName + "', password = '" + password + "' WHERE email = '" + oldEmail + "'";
        return executeUserUpdate(sql);
    }

    // Add updates to DB
    private boolean executeUserUpdate(String sql) {
        Database database = Database.getInstance();
        Connection connection = null;
        try {
            database.connect();
            connection = database.getConnection();
            Statement selectStatement = connection.createStatement();
            selectStatement.executeUpdate(sql);
            database.disconnect();
            return true;
        } catch (SQLException e) {
            System.out.println("executeTaskUpdate Sql exception: task " + e);
            return false;
        } catch (Exception e) {
            System.out.println("executeTaskUpdate Exception: task " + e);
            return false;
        }

    }
}
