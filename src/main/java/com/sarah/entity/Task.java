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

    public void setTaskCategory(String taskCategory) {
        this.taskCategory = taskCategory;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public LocalDate getTaskDueDate() {
        return taskDueDate;
    }

    public void setTaskDueDate(LocalDate taskDueDate) {
        this.taskDueDate = taskDueDate;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(double timeSpent) {
        this.timeSpent = timeSpent;
    }

    public double getEstimatedCompletionTime() {
        return estimatedCompletionTime;
    }

    public void setEstimatedCompletionTime(double estimatedCompletionTime) {
        this.estimatedCompletionTime = estimatedCompletionTime;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public double getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(double timeLeft) {
        this.timeLeft = timeLeft;
    }
}
