package com.sarah.persistence;

import com.sarah.entity.Task;
import com.sarah.entity.TaskEntry;
import com.sarah.utility.DateUtility;

import javax.servlet.RequestDispatcher;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Calculations Class
 * Handles calculations for graphs and calculating estimated completion.
 * Created by Sarah Omernik on 4/4/2017.
 */
public class Calculations {

    /**
     * Adds days of week to map for later use
     * @param timePerDayOfWeek The map to add values to.
     * @return timePerDayOfWeek
     */
    public static Map<String, Double> dayOfWeekSetUp(Map<String, Double> timePerDayOfWeek) {
        Double starter = 0.0;
        timePerDayOfWeek.put("Monday", starter);
        timePerDayOfWeek.put("Tuesday", starter);
        timePerDayOfWeek.put("Wednesday", starter);
        timePerDayOfWeek.put("Thursday", starter);
        timePerDayOfWeek.put("Friday", starter);
        timePerDayOfWeek.put("Saturday", starter);
        timePerDayOfWeek.put("Sunday", starter);

        return timePerDayOfWeek;
    }

    /**
     * Add up the amount of time spent for each day of the week.
     * @param task the current task
     * @param timePerDayOfWeek the map with total time worked per day of week
     * @return timePerDayOfWeek
     */
    public static Map<String, Double> calculateDaysOfWeek(Task task, Map<String, Double> timePerDayOfWeek)
            throws ErrorException {
        TaskEntryData taskEntryData = new TaskEntryData();
        List<TaskEntry> taskEntries = taskEntryData.getUserTaskEntries(String.valueOf(task.getTaskId()));

        for (TaskEntry entry : taskEntries) {
            String day = DateUtility.getDayFromLocalDate(entry.getDateEntered());
            timePerDayOfWeek = addValuesToDayMap(timePerDayOfWeek, entry, day);
        }

        return timePerDayOfWeek;
    }

    /**
     * Adds up the amount of time spent per day
     * @param timePerDayOfWeek map with days and totals
     * @param entry the entry to add
     * @param day the day to add it too
     * @return timePerDayOfWeek
     */
    public static Map<String, Double> addValuesToDayMap(Map<String, Double> timePerDayOfWeek, TaskEntry entry, String day) {
        if (timePerDayOfWeek.containsKey(day)) {
            Double newTotal = timePerDayOfWeek.get(day).intValue() + entry.getTimeAdded();
            timePerDayOfWeek.put(day, newTotal);
        } else {
            timePerDayOfWeek.put(day, entry.getTimeAdded());
        }

        return timePerDayOfWeek;
    }

    /**
     * Updates total time spent per type or category
     * @param task the current task
     * @param totalPerSomeSortingFactor the total so far
     * @param sortingFactor type or category
     * @return totalPerSomeSortingFactor
     */
    public static Map<String, Double> updateTotalPerSomeSortingFactor(Task task, Map<String, Double>totalPerSomeSortingFactor, String sortingFactor) {
        String key;
        // Check sorting factor
        if (sortingFactor.equals("type")) {
            key = task.getTaskType();
        } else {
            key = task.getTaskCategory();
        }

        // Update totals
        if (totalPerSomeSortingFactor.containsKey(key)) {
            Double newTotal = totalPerSomeSortingFactor.get(key).intValue() + task.getTimeSpent();
            totalPerSomeSortingFactor.put(key, newTotal);
        } else {
            totalPerSomeSortingFactor.put(key, (task.getTimeSpent()));
        }

        return totalPerSomeSortingFactor;
    }

    /**
     * Calculates % of time spent on each type
     * @param percentagePerType % value
     * @param totalPerSomeSortingFactor total per type/category
     * @param total total value
     * @return percentagePerType
     */
    public static Map<String, Double> calculatePercentages(Map<String, Double>percentagePerType, Map<String, Double>totalPerSomeSortingFactor, Double total) {
        // Divide total per type by total, then * 100
        for (Map.Entry<String, Double> entry : totalPerSomeSortingFactor.entrySet()) {  // Iterate through hash map
            Double percentage = ((entry.getValue() / total) * 100);
            percentagePerType.put(entry.getKey(), percentage);
        }

        return percentagePerType;
    }

    /**
     * Finds day with most amount of work done
     * @param timePerDayOfWeek map with values of time spent per day
     * @return mostCommonDay
     */
    public static String getMostCommonDay(Map<String, Double> timePerDayOfWeek) {
        Double maxValue = Collections.max(timePerDayOfWeek.values());
        String mostCommonDay = null;

        for (Map.Entry<String, Double> entry : timePerDayOfWeek.entrySet()) {  // Iterate through hashmap
            if (entry.getValue() == maxValue) {
                mostCommonDay = entry.getKey();     // Print the key with max value
            }
        }

        return mostCommonDay;
    }

    /**
     * Determines the date you are estimated to finish a task
     * @param email users email
     * @param timeLeft amount of time left
     * @param taskId the tasks id
     */
    public static double calculateFinishDate(String email, double timeLeft, int taskId, String category, String type, double estimatedCompletionTime) throws ErrorException
    {
        // TODO check for NaN  v != v  (Double.isNaN(doubleValue)
        /*
            avg = average time per entry // how much time is spent working in one entry
            total = # of entries // how many entries have already been made
            diff = # of days // how many days has task been worked on

            entries/day = total / diff // how many entries are completed a day
            # of entries remaining = timeleft / avg // how many entries are left... based on timeleft estimation
            # of days remaining = (# of entries remaining)  / (entries/day) // divide to get # of days

            example:
                timeLeft = 50min

                avg = 15min
                total = 3 entries
                diff = 7 days

                entries/day = 0.428 entries/day ~7.5 min spent working on this task a day
                # entries remaining = 50 / 15 = 3.33 entries (of average size) left
                # days = 3.33 / 0.428 = 7.788 days left (working average of 7.5 min/day)
         */
        TaskEntryData data = new TaskEntryData();
        double numberOfDays = 0.0;

        // For specific task
        double averageTimePerThisTask = data.getAverageOfTimeAddedForTask(email, taskId);
        double totalEntries = data.getTotalEntriesForTask(email, taskId);
        double numberOfDaysDifference = data.getDayDifferenceFromTaskStart(taskId);

        // For all user tasks
        double averageTimePerEntry = data.getAverageOfTimeAdded(email);
        double numberOfDaysDifferenceAll = data.getDayDifferenceForEntryAverages(email);
        double allEntries = data.getTotalEntriesForUser(email);

        // For category + type
        double averageTimePerEntryType = data.getAverageTimePerEntryType(email, type, category); // ex. 25min
        double numberOfDaysDifferenceType = data.getNumberOfDaysDifferenceType(email, type, category); // ex. 14
        double allEntriesCategoryType = data.getAllEntriesCategoryType(email, type, category); // ex. 14

        // days based on entries for 1 task
        double averageEntriesPerDay = totalEntries / numberOfDaysDifference; // ex. 0.71 entries / day
        double numberOfEntriesRemainingThisTask = timeLeft / averageTimePerThisTask; // ex. 75min left / 25 =3
        double numberOfDaysSingleTask = numberOfEntriesRemainingThisTask / averageEntriesPerDay; // 3 / 0.71 = 4.5days


        if (numberOfDaysSingleTask >= 0) {
            numberOfDays = numberOfDaysSingleTask;
        }

        double averageEntriesPerDayCategoryAndType = allEntriesCategoryType / numberOfDaysDifferenceType; // ex. 0.71 entries / day
        double numberOfEntriesRemainingType = timeLeft / averageTimePerEntryType; // ex. 75min left / 25 =3
        double numberOfDaysType = numberOfEntriesRemainingType / averageEntriesPerDayCategoryAndType; // 3 / 0.71 = 4.5days

        if (numberOfDaysSingleTask == 0.0 || totalEntries == 0.0 || numberOfDaysDifference == 0.0
                || numberOfDaysSingleTask != numberOfDaysSingleTask) {

            numberOfDays = numberOfDaysType;
        }

        // days based on all entries
        double averageEntriesPerDayAllTasks = allEntries / numberOfDaysDifferenceAll; // ex. 0.71 entries / day
        double numberOfEntriesRemaining = timeLeft / averageTimePerEntry; // ex. 75min left / 25 =3

        if (timeLeft == 0.0) {
            numberOfEntriesRemaining = estimatedCompletionTime / averageTimePerEntry;
        }

        double numberOfDaysAllTasks = numberOfEntriesRemaining / averageEntriesPerDayAllTasks; // 3 / 0.71 = 4.5days

        if (averageEntriesPerDayCategoryAndType == 0.0 || numberOfEntriesRemainingType == 0.0 || numberOfDaysType == 0.0
                || numberOfDaysType != numberOfDaysType) {

            numberOfDays = numberOfDaysAllTasks;
        }

        // for day in days
        // if day is no work -> +1 day
        // if day is much work -> -1day

        float wholeDays = (float)Math.round(numberOfDays);

        return wholeDays;
    }

    /**
     * Adds up all task entries
     *
     * @param entries the list of entries
     * @return the list of entries
     */
    public static List<Double> getEntries(List<TaskEntry> entries) {
        List<Double> increasingEntries = new ArrayList<Double>();
        if (entries.size() > 0) {
            increasingEntries.add(entries.get(0).getTimeAdded());
            for (int index = 0; index < entries.size(); index++) {
                if (entries.size() > 1 && index > 0) {
                    increasingEntries.add(increasingEntries.get(index - 1) + entries.get(index).getTimeAdded());
                }
            }
        }
        return increasingEntries;
    }
}
