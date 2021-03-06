package com.sarah.persistence;

import com.sarah.entity.TaskEntry;
import com.sarah.utility.DatabaseUtility;
import com.sarah.utility.DateUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * TaskEntryData class
 * Created by Sarah Omernik on 3/8/2017.
 * Connects taskEntry with the database.
 */
public class TaskEntryData {

    /**
     * Retrieves all taskEntries for a single task
     * @param taskId the id of the task
     * @return that tasks entries
     */
    public List<TaskEntry> getUserTaskEntries(String taskId) throws ErrorException {
        List<TaskEntry> taskEntries;
        String sql = "SELECT * FROM taskEntry WHERE taskId = '" + taskId + "'";
        taskEntries = executeQuery(sql);
        return taskEntries;
    }

    /**
     * Executes a query
     * @param sql The statement to get entries from the database.
     * @return a list of entries
     */
    private List<TaskEntry> executeQuery(String sql) throws ErrorException {

        List<TaskEntry> taskEntries = new ArrayList<TaskEntry>();
        Database database = Database.getInstance();
        Connection connection = null;
        try {
            database.connect();
            connection = database.getConnection();
            Statement selectStatement = connection.createStatement();
            ResultSet results = selectStatement.executeQuery(sql);
            retrieveTaskEntries(taskEntries, results);
            database.disconnect();
        } catch (SQLException e) {
            System.out.println("ExecuteQuery TaskEntryData Sql exception: task " + e);
            throw new ErrorException();
        } catch (Exception e) {
            System.out.println("ExecuteQuery TaskEntryData Exception: task " + e);
            e.printStackTrace();
            throw new ErrorException();
        }
        return taskEntries;
    }

    /**
     * Adds each entry to the list of entries
     * @param taskEntries the list of entries
     * @param results the results of the query
     * @throws Exception a general exception
     */
    private void retrieveTaskEntries(List<TaskEntry> taskEntries, ResultSet results) throws Exception {
        while (results.next()) {
            TaskEntry taskEntry = createTaskEntryFromResults(results);
            taskEntries.add(taskEntry);
        }
    }

    /**
     * Sets an entries instance variables with information from the database.
     * @param results the results of the query
     * @return the taskentry
     * @throws Exception a general exception
     */
    private TaskEntry createTaskEntryFromResults(ResultSet results) throws Exception {
        TaskEntry taskEntry  = new TaskEntry();
        taskEntry.setTaskEntryId(results.getInt("taskEntryId"));
        taskEntry.setTaskId(results.getInt("taskId"));
        taskEntry.setDateEntered((results.getDate("dateEntered").toLocalDate()));
        taskEntry.setTimeEntered(DateUtility.formatTimeFromDateTime((results.getString("dateEntered"))));
        taskEntry.setTimeAdded(results.getDouble("timeEnteredAmount"));
        return taskEntry;
    }

    /**
     * Creates sql string to add entry to database
     * @param timeToAdd the amount of time added by the user
     * @param taskId the id of the task this entry is associated with
     * @return boolean if the entry was added
     */
    public boolean addTime(String timeToAdd, String taskId) throws ErrorException {
        String sql = "INSERT INTO taskEntry (taskId, dateEntered, timeEnteredAmount) VALUES (?, ?, ?)";
        return executeAddTimeQuery(sql, timeToAdd, taskId);
    }

    /**
     * Adds entry to database
     * @param sql the query to execute
     * @param timeToAdd the amount of time added by the user
     * @param taskId the id of the task this entry is associated with
     * @return boolean if the entry was added
     */
    private boolean executeAddTimeQuery(String sql, String timeToAdd, String taskId) throws ErrorException {
        Database database = Database.getInstance();
        Connection connection = null;
        try {
            database.connect();
            connection = database.getConnection();
            PreparedStatement insertStatement = connection.prepareStatement(sql);
            insertStatement.setString(1, taskId);
            insertStatement.setString(2, DateUtility.formatDateFromDate());
            insertStatement.setString(3, timeToAdd);
            insertStatement.executeUpdate();
            database.disconnect();
            return true;
        } catch (SQLException e) {
            System.out.println("executeAddTimeQuery Sql exception: task " + e);
            throw new ErrorException();
        } catch (Exception e) {
            System.out.println("executeAddTimeQuery Exception: task " + e);
            e.printStackTrace();
            throw new ErrorException();
        }

    }

    /**
     * Gets average time spent on all user tasks.
     * @param email user email
     * @return value of average
     * @throws ErrorException if error
     */
    public double getAverageOfTimeAdded(String email) throws ErrorException {
        String sql = "SELECT AVG(timeEnteredAmount) FROM taskentry INNER JOIN task ON task.taskId=taskentry.taskId INNER" +
                " JOIN user ON task.userId=user.userId WHERE user.email ='" + email + "'";

        double averageEntryTime = DatabaseUtility.executeSingleQuery(sql, "AVG(timeEnteredAmount)");
        return averageEntryTime;
    }

    /**
     * Get # of entries for user
     * @param email user email
     * @return value of total
     * @throws ErrorException if error
     */
    public double getTotalEntriesForUser(String email) throws ErrorException {
        String sql = "SELECT COUNT(*) FROM taskentry WHERE taskentry.taskid IN (SELECT task.taskId FROM task INNER JOIN "
                + "user ON task.userId=user.userId WHERE user.email='" + email + "')";

        double countOfAllEntriesForUser = DatabaseUtility.executeSingleQuery(sql, "COUNT(*)");

        return countOfAllEntriesForUser;
    }

    /**
     * Get difference between today and start of all tasks
     * @param email user email
     * @return value of difference
     * @throws ErrorException if error
     */
    public double getDayDifferenceForEntryAverages(String email) throws ErrorException {
        String sql = "SELECT DATEDIFF(NOW(), (SELECT MIN(dateentered) FROM taskentry WHERE taskentry.taskid IN (SELECT task.taskId FROM task INNER JOIN "
                + "user ON task.userId=user.userId WHERE user.email='" + email + "'))) AS datediff";

        double diffOfAllTasks = DatabaseUtility.executeSingleQuery(sql, "datediff");
        return diffOfAllTasks;
    }

    /**
     * Gets average time based on category and type
     * @param email user email
     * @param type item type
     * @param category item category
     * @return value of average
     * @throws ErrorException if error
     */
    public double getAverageTimePerEntryType(String email, String type, String category) throws ErrorException {
        String sql = "SELECT AVG(timeEnteredAmount) FROM taskentry INNER JOIN task ON task.taskId=taskentry.taskId INNER"
                +" JOIN user ON task.userId=user.userId WHERE user.email ='" + email + "' AND task.category='" + category
                + "' AND typeId = (SELECT typeId FROM type WHERE typeName='" + type + "')";

        double averageEntryTime = DatabaseUtility.executeSingleQuery(sql, "AVG(timeEnteredAmount)");
        return averageEntryTime;
    }

    /**
     * Gets difference from today to start of type/category entries
     * @param email user email
     * @param type item type
     * @param category item category
     * @return value of difference
     * @throws ErrorException if error
     */
    public double getNumberOfDaysDifferenceType(String email, String type, String category) throws ErrorException {
        String sql = "SELECT DATEDIFF(NOW(), (SELECT MIN(dateentered) FROM taskentry WHERE taskentry.taskid IN (SELECT task.taskId FROM task INNER JOIN "
                + "user ON task.userId=user.userId WHERE user.email='" + email + "' AND task.category='" + category
                + "' AND typeId = (SELECT typeId FROM type WHERE typeName='" + type + "')))) AS datediff";

        double diffOfAllTasks = DatabaseUtility.executeSingleQuery(sql, "datediff");
        return diffOfAllTasks;
    }

    /**
     * Gets count of all entries of type/category
     * @param email user email
     * @param type item type
     * @param category item category
     * @return value of count
     * @throws ErrorException if error
     */
    public double getAllEntriesCategoryType(String email, String type, String category) throws ErrorException {
        String sql = "SELECT COUNT(*) FROM taskentry WHERE taskentry.taskid IN (SELECT task.taskId FROM task INNER JOIN "
                + "user ON task.userId=user.userId WHERE user.email='" + email + "' AND task.category='" + category
                + "' AND typeId = (SELECT typeId FROM type WHERE typeName='" + type + "'))";

        double countOfAllEntriesForType = DatabaseUtility.executeSingleQuery(sql, "COUNT(*)");

        return countOfAllEntriesForType;
    }

    /**
     * Get average of time for this task
     * @param email user email
     * @param taskId task id
     * @return value of average
     * @throws ErrorException if error
     */
    public double getAverageOfTimeAddedForTask(String email, int taskId) throws ErrorException {
        String sql = "SELECT AVG(timeEnteredAmount) FROM taskentry INNER JOIN task ON task.taskId=taskentry.taskId INNER" +
                " JOIN user ON task.userId=user.userId WHERE user.email ='" + email + "' AND taskentry.taskId = " + taskId;

        double averageEntryTime = DatabaseUtility.executeSingleQuery(sql, "AVG(timeEnteredAmount)");
        return averageEntryTime;
    }

    /**
     * Get # of entries for this task.
     * @param email user email
     * @param taskId the task id
     * @return value of count
     * @throws ErrorException if error
     */
    public double getTotalEntriesForTask(String email, int taskId) throws ErrorException {
        String sql = "SELECT COUNT(*) as count FROM taskentry INNER JOIN task ON task.taskId=taskentry.taskId INNER JOIN "
                + "user ON task.userId=user.userId WHERE taskentry.taskId=" + taskId + " AND user.email='" + email + "'";
        double countOfEntriesForSingleTask = DatabaseUtility.executeSingleQuery(sql, "count");

        return countOfEntriesForSingleTask;
    }

    /**
     * Gets difference from today to start of this task
     * @param taskId task id
     * @return value of difference
     * @throws ErrorException if error
     */
    public double getDayDifferenceFromTaskStart(int taskId) throws ErrorException {
        String sql = "SELECT DATEDIFF(NOW(), (SELECT MIN(dateentered) FROM taskentry INNER JOIN task ON "
                + "task.taskId=taskentry.taskId WHERE task.taskid=" + taskId + ")) AS datediff";

        double diffOfDays = DatabaseUtility.executeSingleQuery(sql, "datediff");
        return diffOfDays;
    }

}
