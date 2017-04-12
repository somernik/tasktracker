package com.sarah.utility;

import com.sarah.persistence.Database;

import java.sql.Connection;
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
    public static boolean executeUpdate(String sql) {
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
