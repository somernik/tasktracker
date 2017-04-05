package com.sarah.entity;

import java.time.LocalDate;

/**
 * TaskEntry class
 * Created by Sarah Omernik on 2/8/2017.
 * Holds information about each task entry
 */
public class TaskEntry {
    private double timeAdded;
    private String timeEntered;
    private LocalDate dateEntered;

    private int taskEntryId;
    private int taskId;

    /**
     * Empty constructor for TaskEntry
     */
    public TaskEntry() {
    }

    /**
     * Constructor with parameters for TaskEntry
     * @param timeAdded The amount of time added for this entry
     * @param dateEntered The date this entry was added
     * @param taskEntryId The id of this entry
     * @param taskId The id of the task this entry is associated with
     * @param timeEntered The time this entry was added
     */
    public TaskEntry(double timeAdded, LocalDate dateEntered,
                     int taskEntryId, int taskId, String timeEntered) {
        this.dateEntered = dateEntered;
        this.taskId = taskId;
        this.taskEntryId = taskEntryId;
        this.timeAdded = timeAdded;
        this.timeEntered = timeEntered;
    }

    /**
     * Getter for timeAdded
     * @return timeAdded
     */
    public double getTimeAdded() {
        return timeAdded;
    }

    /**
     * Setter for timeAdded
     * @param timeAdded the amount of time added
     */
    public void setTimeAdded(double timeAdded) {
        this.timeAdded = timeAdded;
    }

    /**
     * Getter for timeEntered
     * @return timeEntered
     */
    public String getTimeEntered() {
        return timeEntered;
    }

    /**
     * Setter for timeEntered
     * @param timeEntered the time the entry was added
     */
    public void setTimeEntered(String timeEntered) {
        this.timeEntered = timeEntered;
    }

    /**
     * Getter for dateEntered
     * @return dateEntered
     */
    public LocalDate getDateEntered() {
        return dateEntered;
    }

    /**
     * Setter for dateEntered
     * @param dateEntered the date the entry was added
     */
    public void setDateEntered(LocalDate dateEntered) {
        this.dateEntered = dateEntered;
    }

    /**
     * Getter for taskEntryId
     * @return taskEntryId
     */
    public int getTaskEntryId() {
        return taskEntryId;
    }

    /**
     * Setter for taskEntryId
     * @param taskEntryId The id of this taskentry
     */
    public void setTaskEntryId(int taskEntryId) {
        this.taskEntryId = taskEntryId;
    }

    /**
     * Getter for taskId
     * @return taskid
     */
    public int getTaskId() {
        return taskId;
    }

    /**
     * Setter for taskId
     * @param taskId The id of the task associated with this entry
     */
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}