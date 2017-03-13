package com.sarah.persistence;

import com.sarah.entity.TaskEntry;
import com.sarah.persistence.Utility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarah Omernik on 3/8/2017.
 */
public class TaskEntryData {

    public List<TaskEntry> getUserTaskEntries(String taskId) {
        List<TaskEntry> taskEntries;
        String sql = "SELECT * FROM taskEntry WHERE taskId = '" + taskId + "'";
        taskEntries = executeQuery(sql);
        return taskEntries;
    }

    private List<TaskEntry> executeQuery(String sql) {

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
        } catch (Exception e) {
            System.out.println("ExecuteQuery TaskEntryData Exception: task " + e);
            e.printStackTrace();
        }
        return taskEntries;
    }

    private void retrieveTaskEntries(List<TaskEntry> tasks, ResultSet results) throws Exception {
        while (results.next()) {
            TaskEntry taskEntry = createTaskEntryFromResults(results);
            tasks.add(taskEntry);
        }
    }

    private TaskEntry createTaskEntryFromResults(ResultSet results) throws Exception {
        Utility utility = new Utility();
        TaskEntry taskEntry  = new TaskEntry();
        taskEntry.setTaskEntryId(results.getInt("taskEntryId"));
        taskEntry.setTaskId(results.getInt("taskId"));
        taskEntry.setDateEntered((results.getDate("dateEntered").toLocalDate()));
        taskEntry.setTimeEntered(utility.formatTimeFromDateTime((results.getString("dateEntered"))));
        taskEntry.setTimeAdded(results.getDouble("timeEnteredAmount"));
        return taskEntry;
    }

    public boolean addTime(String timeToAdd, String taskId) {
        String sql = "INSERT INTO taskEntry (taskId, dateEntered, timeEnteredAmount) VALUES (?, ?, ?)";
        return executeAddTimeQuery(sql, timeToAdd, taskId);
    }

    private boolean executeAddTimeQuery(String sql, String timeToAdd, String taskId) {
        Utility utility = new Utility();
        Database database = Database.getInstance();
        Connection connection = null;
        try {
            database.connect();
            connection = database.getConnection();
            PreparedStatement insertStatement = connection.prepareStatement(sql);
            insertStatement.setString(1, taskId);
            insertStatement.setString(2, utility.formatDateFromDate());
            insertStatement.setString(3, timeToAdd);
            insertStatement.executeUpdate();
            database.disconnect();
            return true;
        } catch (SQLException e) {
            System.out.println("executeAddTimeQuery Sql exception: task " + e);
            return false;
        } catch (Exception e) {
            System.out.println("executeAddTimeQuery Exception: task " + e);
            e.printStackTrace();
            return false;
        }

    }
}
