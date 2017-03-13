package com.sarah.entity;

import java.time.LocalDate;

/**
 * Created by Sarah Omernik on 2/8/2017.
 * TaskEntry class
 */
public class TaskEntry {
    private double timeAdded;
    private String timeEntered;
    private LocalDate dateEntered;

    private int taskEntryId;
    private int taskId;

    public TaskEntry() {
    }

    public TaskEntry(double timeAdded, LocalDate dateEntered,
                     int taskEntryId, int taskId) {
        this.dateEntered = dateEntered;
        this.taskId = taskId;
        this.taskEntryId = taskEntryId;
        this.timeAdded = timeAdded;
    }

    public double getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(double timeAdded) {
        this.timeAdded = timeAdded;
    }

    public String getTimeEntered() {
        return timeEntered;
    }

    public void setTimeEntered(String timeEntered) {
        this.timeEntered = timeEntered;
    }

    public LocalDate getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(LocalDate dateEntered) {
        this.dateEntered = dateEntered;
    }

    public int getTaskEntryId() {
        return taskEntryId;
    }

    public void setTaskEntryId(int taskEntryId) {
        this.taskEntryId = taskEntryId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}