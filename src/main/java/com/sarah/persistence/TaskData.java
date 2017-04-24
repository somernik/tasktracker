package com.sarah.persistence;

import com.sarah.entity.Task;
import com.sarah.utility.DatabaseUtility;
import com.sarah.utility.DateUtility;

import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;


/**
 * Created by Sarah Omernik on 2/8/2017.
 */
public class TaskData {
    //private final Logger logger = Logger.getLogger(this.getClass());

    /**
     * Creates sql query to add new task into database
     * @param name name of task
     * @param category category of task
     * @param type type of task
     * @param description description of task
     * @param dueDate duedate of task
     * @param email email of user
     * @return boolean if value was added
     * @throws Exception general exception
     */
    public boolean addNewTask(String name, String category, String type, String description, String dueDate, Object email) throws ErrorException {
        String sql = "INSERT INTO task (userId, name, typeId, category, description, dueDate, startDate) VALUES ("
                + "(SELECT userId FROM user WHERE email='" + email + "'), '" + name + "', '" + type + "', '"
                + category + "', '" + description + "', '" + dueDate + "', NOW())";
        return DatabaseUtility.executeUpdate(sql);
    }

    /**
     * Creates query to update a single task
     * @param taskId id of the task to update
     * @param name task name
     * @param category category of task
     * @param type type of task
     * @param description description of task
     * @param dueDate due date for task
     * @param completed if task is completed
     * @param startDate date task is started
     * @param timeAdded if time is added
     * @return boolean if edit was successful
     * @throws Exception general exception
     */
    public boolean editSingleTask(String taskId, String name, String category, String type, String description,
                                  String dueDate, String completed, String startDate, String timeAdded) throws Exception {

        TaskEntryData taskEntryData = new TaskEntryData();
        String sql = "UPDATE task SET name = '" + name + "', typeId = (SELECT typeId FROM type WHERE"
                + " typeName='" + type + "'), category = '" + category + "', description = '" + description
                + "', dueDate = '" + dueDate + "', completed = '" + completed + "', startDate= '" + startDate
                + "' WHERE taskId = '" + taskId + "'";
        if (timeAdded.matches("-?\\d+(\\.\\d+)?")) {
            taskEntryData.addTime(timeAdded, taskId);
            updateTimeSpent(taskId);
        }
        return DatabaseUtility.executeUpdate(sql);
    }

    /**
     * Retrieves all tasks for a single user
     * @param userEmail users email
     * @param search search criteria for filter options
     * @return list of tasks
     */
    public List<Task> getUserTasks(Object userEmail, String search) throws ErrorException {
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

        // Updates estimated time based on all tasks
        for (Task task : tasks) {
            double estimatedTime = calculateEstimatedTime(task.getTaskType(), task.getTaskCategory());
            if ((task.getEstimatedCompletionTime() == 0 && estimatedTime >= 0) || (task.getEstimatedCompletionTime() >= 0 && estimatedTime > 0)) {
                // if get > 0 and calc = 0  --> user set estimated value but calculated value is 0, dont update because this is first task that type
                updateEstimatedCompletionTime(task, estimatedTime);
                task.setEstimatedCompletionTime(estimatedTime);
            }
        }
        return tasks;
    }


    /**
     * Execute a query getting tasks
     * @param sqlStatement sql query to get tasks
     * @return a list of tasks
     */
    private List<Task> executeQuery(String sqlStatement) throws ErrorException {

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
            throw new ErrorException();
        } catch (Exception e) {
            System.out.println("ExecuteQuery Exception: task " + e);
            e.printStackTrace();
            throw new ErrorException();
        }
        return tasks;
    }

    /**
     * Adds new tasks from results to list
     * @param tasks list of tasks
     * @param results result set of tasks
     * @throws SQLException for sql syntax errors
     */
    private void retrieveTasks(List<Task> tasks, ResultSet results) throws SQLException {
        while (results.next()) {
            Task userTask = createTaskFromResults(results);
            tasks.add(userTask);
        }
    }

    /**
     * Creates a new task from database
     * @param results the result set
     * @return Task
     * @throws SQLException for errors in SQL syntax
     */
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
        task.setTimeLeft(task.getEstimatedCompletionTime() - task.getTimeSpent());
        return task;
    }


    /**
     * Updates how much time has been spent on a task
     * @param taskId the id of the task
     * @return boolean if executed successfully
     */
    public boolean updateTimeSpent(String taskId) throws ErrorException {
        String sql = "UPDATE task SET cumulativeTimeSpent = (SELECT SUM(timeEnteredAmount) FROM taskEntry "
                + "WHERE taskEntry.taskId=" + taskId + ") WHERE taskId=" + taskId + ";";
        return DatabaseUtility.executeUpdate(sql);

    }

    /**
     * Calculates estimated time
     * @param type type of tasks for grouping
     * @param category category of tasks for grouping
     * @return value of estimated time to complete task
     */
    public double calculateEstimatedTime(String type, String category) throws ErrorException {
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

    /**
     * Creates sql to update completion time
     * @param task task to update
     * @param estimatedTime the value to update to
     */
    public void updateEstimatedCompletionTime(Task task, double estimatedTime) throws ErrorException {
        String sql = "UPDATE task SET estimatedCompletionTime = '" + estimatedTime + "' WHERE taskId = '" + task.getTaskId() + "'";
        DatabaseUtility.executeUpdate(sql);
    }

    /**
     * Creates sql to allow grouped statments
     * @param type type of task to group
     * @param category category of task to group
     * @param columnName column name to group/count on
     * @return value of the grouping
     */
    public double groupSqlStatement(String type, String category, String columnName) throws ErrorException{
        double totalTime;

        String sql = "SELECT " + columnName + " FROM task JOIN type ON task.typeId=type.typeId WHERE typeName='" + type + "' AND category='" + category + "' AND completed=1";
        totalTime = DatabaseUtility.executeSingleQuery(sql, columnName);

        return totalTime;
    }


    /**
     * Gets 1 task from database
     * @param taskId the id of the task to get
     * @return a task
     */
    public Task getSingleTask(String taskId) throws ErrorException {
        List<Task> tasks = new ArrayList<Task>();
        Task task;
        String sql = "SELECT * FROM task INNER JOIN type ON task.typeId=type.typeId INNER JOIN user ON "
                + "task.userID=user.userID WHERE taskId = " + taskId;
        tasks = executeQuery(sql);
        task = tasks.get(0);
        return task;
    }


    public String addType(String typeName, String email) throws ErrorException {

        String typeSql = "INSERT INTO type (typeName) VALUES ('" + typeName + "')";
        String userTypeSql = "INSERT INTO usertype (typeId, userId) VALUES ((SELECT typeId FROM type WHERE typeName='" + typeName + "' LIMIT 1), (SELECT userId FROM user WHERE email='" + email + "'))";

        DatabaseUtility.executeUpdate(typeSql);
        DatabaseUtility.executeUpdate(userTypeSql);

        // get types id
        String findTypeId = "SELECT typeId FROM type WHERE typeName='" + typeName + "'";
        Double typeId = DatabaseUtility.executeSingleQuery(findTypeId, "typeId");

        return typeId.toString();
    }

    public Map<String, String> getTypes(String email) throws ErrorException {
        Map<String, String> types = new HashMap<String, String>();
        String sql = "SELECT * FROM type WHERE NOT EXISTS (SELECT usertype.typeId FROM usertype WHERE type.typeId=usertype.typeId)";

        executeTypesQuery(sql, types);

        String userOnlySql = "SELECT usertype.typeId, typeName FROM usertype INNER JOIN type ON type.typeId=usertype.typeID INNER JOIN user ON usertype.userId=user.userID WHERE user.email='"
                + email + "'";

        executeTypesQuery(userOnlySql, types);

        return types;
    }


    private Map<String, String> executeTypesQuery(String sqlStatement, Map<String, String> types) throws ErrorException {

        Database database = Database.getInstance();
        Connection connection = null;
        try {
            database.connect();
            connection = database.getConnection();
            Statement selectStatement = connection.createStatement();
            ResultSet results = selectStatement.executeQuery(sqlStatement);
            retrieveTypes(types, results);
            database.disconnect();
        } catch (SQLException e) {
            System.out.println("ExecuteQuery Sql exception: task " + e);
            throw new ErrorException();
        } catch (Exception e) {
            System.out.println("ExecuteQuery Exception: task " + e);
            e.printStackTrace();
            throw new ErrorException();
        }
        return types;
    }

    private void retrieveTypes(Map<String, String> types, ResultSet results) throws SQLException {
        while (results.next()) {
            types.put(results.getString("typeId"), results.getString("typeName"));
        }
    }

    public List<String> getCategories(String email) throws ErrorException {
        List<String> categories = new ArrayList<String>();
        String sql = "SELECT DISTINCT category FROM task INNER JOIN user ON task.userId=user.userID WHERE user.email='" + email + "'";

        categories = executeCategoriesQuery(sql, categories);

        return categories;
    }

    private List<String> executeCategoriesQuery(String sql, List<String> categories) throws ErrorException {
        Database database = Database.getInstance();
        Connection connection = null;
        try {
            database.connect();
            connection = database.getConnection();
            Statement selectStatement = connection.createStatement();
            ResultSet results = selectStatement.executeQuery(sql);
            retrieveCategories(categories, results);
            database.disconnect();
        } catch (SQLException e) {
            System.out.println("ExecuteQuery Sql exception: task " + e);
            throw new ErrorException();
        } catch (Exception e) {
            System.out.println("ExecuteQuery Exception: task " + e);
            e.printStackTrace();
            throw new ErrorException();
        }
        return categories;
    }

    private void retrieveCategories(List<String> categories, ResultSet results) throws SQLException {
        while (results.next()) {
            categories.add(results.getString("category"));
        }
    }
}