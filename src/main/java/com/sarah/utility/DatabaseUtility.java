package com.sarah.utility;

import com.sarah.persistence.Database;
import com.sarah.persistence.ErrorException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DatabaseUtility
 * Created by Sarah Omernik on 4/12/2017.
 */
public class DatabaseUtility {
    /**
     * Executes generic sql statement
     * @param sql sql query to execute
     * @return boolean if completed successfully
     */
    public static boolean executeUpdate(String sql) throws ErrorException {
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
            throw new ErrorException();
        } catch (Exception e) {
            System.out.println("executeTaskUpdate Exception: task " + e);
            throw new ErrorException();
        }

    }


    /**
     * Executes single query and gets its return value
     * @param sql the query to execute
     * @param columnName the name of column to count
     * @return value returned from query
     */
    public static double executeSingleQuery(String sql, String columnName) throws ErrorException {
        ResultSet resultSet;
        double value = 0;
        Database database = Database.getInstance();
        Connection connection = null;
        try {
            database.connect();
            connection = database.getConnection();
            Statement selectStatement = connection.createStatement();
            resultSet = selectStatement.executeQuery(sql);
            resultSet.next();
            value = resultSet.getDouble(columnName);
            database.disconnect();
        } catch (SQLException e) {
            System.out.println("ExecuteQuery Sql exception: task " + e);
            throw new ErrorException();
        } catch (Exception e) {
            System.out.println("ExecuteQuery Exception: task " + e);
            e.printStackTrace();
            throw new ErrorException();
        }

        return value;
    }
}
