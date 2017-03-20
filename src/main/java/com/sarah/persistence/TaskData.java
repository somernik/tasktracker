package com.sarah.persistence;

import com.sarah.entity.Task;
import com.sarah.persistence.Utility;

import org.apache.log4j.Logger;

import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Date;

/*import java.util.ArrayList;

import java.util.List;*/

/**
 * Created by Sarah Omernik on 2/8/2017.
 */
public class TaskData {
    //private final Logger logger = Logger.getLogger(this.getClass());
    private Utility utility = new Utility();

    // Create a task
    public boolean addNewTask(String name, String category, String type, String description, String dueDate, Object email) throws Exception {
        String sql = "INSERT INTO task (userId, name, typeId, category, description, dueDate, startDate) VALUES ("
                + "(SELECT userId FROM user WHERE email='" + email + "'), '" + name + "', (SELECT typeId FROM type WHERE"
                + " typeName='" + type + "'), '" + category + "', '" + description + "', '" + dueDate + "', NOW())";
        return executeTaskUpdate(sql);
    }

    // Update a task
    public boolean editSingleTask(String taskId, String name, String category, String type, String description, String dueDate, String completed, String startDate, String timeAdded) throws Exception {

        TaskEntryData taskEntryData = new TaskEntryData();
        String sql = "UPDATE task SET name = '" + name + "', typeId = (SELECT typeId FROM type WHERE"
                + " typeName='" + type + "'), category = '" + category + "', description = '" + description
                + "', dueDate = '" + dueDate + "', completed = '" + completed + "', startDate= '" + startDate
                + "' WHERE taskId = '" + taskId + "'";
        if (timeAdded.matches("-?\\d+(\\.\\d+)?")) {
            taskEntryData.addTime(timeAdded, taskId);
            updateTimeSpent(taskId);
        }
        return executeTaskUpdate(sql);
    }

    // Add task to DB
    private boolean executeTaskUpdate(String sql) {
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

    public List<Task> getUserTasks(Object userEmail, String search) {
        List<Task> tasks;
        String sql;
        if (search.equals("active")) {
            sql = "SELECT * FROM task INNER JOIN type ON task.typeId=type.typeId INNER JOIN user ON "
                    + "task.userID=user.userID WHERE email = '" + userEmail + "' AND completed=0";
        } else {
            sql = "SELECT * FROM task INNER JOIN type ON task.typeId=type.typeId INNER JOIN user ON "
                    + "task.userID=user.userID WHERE email = '" + userEmail + "' AND " + search;
        }
        System.out.print(sql);
        tasks = executeQuery(sql);
        for (Task task : tasks) {
            double estimatedTime = calculateEstimatedTime(task.getTaskType(), task.getTaskCategory());
            updateEstimatedCompletionTime(task, estimatedTime);
            task.setEstimatedCompletionTime(estimatedTime);

        }
        return tasks;
    }


    private List<Task> executeQuery(String sqlStatement) {

        List<Task> tasks = new ArrayList<Task>();
        Database database = Database.getInstance();
        Connection connection = null;
        try {
            database.connect();
            connection = database.getConnection();
            Statement selectStatement = connection.createStatement();
            ResultSet results = selectStatement.executeQuery(sqlStatement);
            retrieveTasks(tasks, results);
            database.disconnect();
        } catch (SQLException e) {
            System.out.println("ExecuteQuery Sql exception: task " + e);
        } catch (Exception e) {
            System.out.println("ExecuteQuery Exception: task " + e);
            e.printStackTrace();
        }
        return tasks;
    }

    private void retrieveTasks(List<Task> tasks, ResultSet results) throws SQLException {
        while (results.next()) {
            Task userTask = createTaskFromResults(results);
            tasks.add(userTask);
        }
    }

    private Task createTaskFromResults(ResultSet results) throws SQLException {
        Task task = new Task();
        task.setTaskName(results.getString("name"));
        task.setTaskId(results.getInt("taskId"));
        task.setTaskCategory(results.getString("category"));
        task.setTaskType(results.getString("typeName"));
        task.setTaskDescription(results.getString("description"));
        task.setTaskDueDate(results.getDate("dueDate").toLocalDate());
        task.setTimeSpent(results.getDouble("cumulativeTimeSpent"));
        task.setEstimatedCompletionTime(results.getDouble("estimatedCompletionTime"));
        task.setStartDate(results.getDate("startDate").toLocalDate());
        task.setCompleted(results.getBoolean("completed"));
        return task;
    }


    public boolean updateTimeSpent(String taskId) {
        String sql = "UPDATE task SET cumulativeTimeSpent = (SELECT SUM(timeEnteredAmount) FROM taskEntry "
                + "WHERE taskEntry.taskId=" + taskId + ") WHERE taskId=" + taskId + ";";
        return executeTaskUpdate(sql);

    }

    public double calculateEstimatedTime(String type, String category) {
        String timeColumn = "SUM(cumulativeTimeSpent)";
        String numberColumn = "COUNT(taskId)";
        double estimatedTime;
        double totalTime = groupSqlStatement(type, category, timeColumn);
        double numberOfTasks = groupSqlStatement(type, category, numberColumn);
        if (numberOfTasks == 0) {
            estimatedTime = 0;
        } else {
            estimatedTime = (totalTime / numberOfTasks);
        }
        return (estimatedTime);

    }

    public void updateEstimatedCompletionTime(Task task, double estimatedTime) {
        String sql = "UPDATE task SET estimatedCompletionTime = '" + estimatedTime + "' WHERE taskId = '" + task.getTaskId() + "'";
        executeTaskUpdate(sql);
    }

    public double groupSqlStatement(String type, String category, String columnName) {
        double totalTime;

        String sql = "SELECT " + columnName + " FROM task JOIN type ON task.typeId=type.typeId WHERE typeName='" + type + "' AND category='" + category + "' AND completed=1";
        totalTime = executeSingleQuery(sql, columnName);

        return totalTime;
    }

    private double executeSingleQuery(String sql, String columnName) {
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
        } catch (Exception e) {
            System.out.println("ExecuteQuery Exception: task " + e);
            e.printStackTrace();
        }

        return value;
    }

    public Task getSingleTask(String taskId) {
        List<Task> tasks = new ArrayList<Task>();
        Task task;
        String sql = "SELECT * FROM task INNER JOIN type ON task.typeId=type.typeId INNER JOIN user ON "
                + "task.userID=user.userID WHERE taskId = " + taskId;
        tasks = executeQuery(sql);
        task = tasks.get(0);
        return task;
    }

}