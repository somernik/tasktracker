package com.sarah.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.*;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Utility class
 * Utility functions, mostly for dates, days, and times.
 * Created by Sarah Omernik on 3/8/2017.
 */
public class Utility {
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

    // Formatting functions
    /**
     * Formats dates into database friendly format
     * @return The formatted date
     * @throws Exception General exception
     */
    public static String formatDateFromDate() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     * Changes a full date and time into just the time part.
     * @param inputDateTime The date and time value.
     * @return Formatted time string
     * @throws Exception for general exceptions
     */
    public static String formatTimeFromDateTime(String inputDateTime) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parsedDate = dateFormat.parse(inputDateTime);
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        return timeFormat.format(parsedDate);
    }

    /**
     * Changes the date value of a day into a day fully written out.
     * @param localDate The date.
     * @return A day of the week, fully written out.
     */
    public static String getDayFromLocalDate(LocalDate localDate) {

        DayOfWeek dayOfWeek = localDate.getDayOfWeek();  // Extracts a `DayOfWeek` enum object.
        return dayOfWeek.getDisplayName(TextStyle.FULL, Locale.US); // String = Tuesday
    }
}
