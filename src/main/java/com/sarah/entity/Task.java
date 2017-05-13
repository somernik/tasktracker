package com.sarah.entity;

import java.time.LocalDate;

/**
 * class Task
 * Created by Sarah Omernik on 2/8/2017.
 * Holds all information about a Task
 */
public class Task {
    private String taskName;
    private String taskCategory;
    private String taskType;
    private String taskDescription;
    private LocalDate taskDueDate;

    private int taskId;
    private int userId;
    private double timeSpent;
    private double estimatedCompletionTime;
    private LocalDate startDate;
    private boolean completed;
    private double timeLeft;

    /**
     * Empty constructor for Task
     */
    public Task() {
    }

    /**
     * Constructor with parameters for Task
     * @param taskName The name of the task
     * @param taskCategory The tasks category
     * @param taskType The tasks type
     * @param taskDescription Description of task
     * @param taskDueDate Due date for task
     * @param taskId Tasks id
     * @param userId Id of the tasks user
     * @param timeSpent Amount of time user has worked on this task
     * @param estimatedCompletionTime How much time this task is estimated to take
     * @param startDate When the task was started, defaults to day the task is created
     * @param completed Boolean value of whether the task is completed
     */
    public Task(String taskName, String taskCategory, String taskType, String taskDescription, LocalDate taskDueDate,
                int taskId, int userId, double timeSpent, double estimatedCompletionTime, LocalDate startDate, boolean completed) {
        this.taskName = taskName;
        this.taskCategory = taskCategory;
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.taskDueDate = taskDueDate;
        this.taskId = taskId;
        this.userId = userId;
        this.timeSpent = timeSpent;
        this.estimatedCompletionTime = estimatedCompletionTime;
        this.startDate = startDate;
        this.completed = completed;
    }

    /**
     * Getter for taskName
     * @return taskName
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Setter for taskName
     * @param taskName The name of the task
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Getter for task category
     * @return taskCategory
     */
    public String getTaskCategory() {
        return taskCategory;
    }

    /**
     * Setter for task category
     * @param taskCategory task category
     */
    public void setTaskCategory(String taskCategory) {
        this.taskCategory = taskCategory;
    }

    /**
     * Getter for task type
     * @return task type
     */
    public String getTaskType() {
        return taskType;
    }

    /**
     * Setter for task type
     * @param taskType type
     */
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    /**
     * Getter for task description
     * @return description
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Setter for task description
     * @param taskDescription description
     */
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Getter for task due date
     * @return due date
     */
    public LocalDate getTaskDueDate() {
        return taskDueDate;
    }

    /**
     * Setter for due date
     * @param taskDueDate due date
     */
    public void setTaskDueDate(LocalDate taskDueDate) {
        this.taskDueDate = taskDueDate;
    }

    /**
     * Getter for task id
     * @return id
     */
    public int getTaskId() {
        return taskId;
    }

    /**
     * Setter for task id
     * @param taskId id
     */
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Getter for user id
     * @return user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Setter for user id
     * @param userId user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Getter for time spent
     * @return time spent
     */
    public double getTimeSpent() {
        return timeSpent;
    }

    /**
     * Setter for time spent
     * @param timeSpent time spent
     */
    public void setTimeSpent(double timeSpent) {
        this.timeSpent = timeSpent;
    }

    /**
     * Getter for estimated completion time
     * @return estimated completion time
     */
    public double getEstimatedCompletionTime() {
        return estimatedCompletionTime;
    }

    /**
     * Setter for estimated completion time
     * @param estimatedCompletionTime estimated completion time
     */
    public void setEstimatedCompletionTime(double estimatedCompletionTime) {
        this.estimatedCompletionTime = estimatedCompletionTime;
    }

    /**
     * Getter for start date
     * @return start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Setter for start date
     * @param startDate start date
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Getter for completed
     * @return completed
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Setter for completed
     * @param completed completed
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Getter for time left
     * @return time left
     */
    public double getTimeLeft() {
        return timeLeft;
    }

    /**
     * Setter for time left
     * @param timeLeft time left
     */
    public void setTimeLeft(double timeLeft) {
        this.timeLeft = timeLeft;
    }
}
