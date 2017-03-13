package com.sarah.entity;

import java.time.LocalDate;

/**
 * Created by Sarah Omernik on 2/8/2017.
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

    public Task() {
    }

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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

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


    public double estimateTimeLeft() {
        return (estimatedCompletionTime - timeSpent);
    }



}
