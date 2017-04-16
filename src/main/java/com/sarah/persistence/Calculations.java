package com.sarah.persistence;

import com.sarah.entity.Task;
import com.sarah.entity.TaskEntry;
import com.sarah.utility.DateUtility;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Calculations Class
 * Created by Sarah Omernik on 4/4/2017.
 */
public class Calculations {
    // TODO add javadoc

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

    public static Map<String, Double> calculateDaysOfWeek(Task task, Map<String, Double> timePerDayOfWeek) {
        TaskEntryData taskEntryData = new TaskEntryData();
        List<TaskEntry> taskEntries = taskEntryData.getUserTaskEntries(String.valueOf(task.getTaskId()));
        for (TaskEntry entry : taskEntries) {
            String day = DateUtility.getDayFromLocalDate(entry.getDateEntered());
            addValuesToDayMap(timePerDayOfWeek, entry, day);
        }

        return timePerDayOfWeek;
    }

    public static void addValuesToDayMap(Map<String, Double> timePerDayOfWeek, TaskEntry entry, String day) {
        if (timePerDayOfWeek.containsKey(day)) {
            Double newTotal = timePerDayOfWeek.get(day).intValue() + entry.getTimeAdded();
            timePerDayOfWeek.put(day, newTotal);
        } else {
            timePerDayOfWeek.put(day, entry.getTimeAdded());
        }
    }

    public static Map<String, Double> updateTotalPerSomeSortingFactor(Task task, Map<String, Double>totalPerSomeSortingFactor, String sortingFactor) {
        String key;
        // Check sorting factor
        // Pull into own function?
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

    public static Map<String, Double> calculatePercentages(Map<String, Double>percentagePerType, Map<String, Double>totalPerSomeSortingFactor, Double total) {
        // Divide total per type by total, then * 100
        for (Map.Entry<String, Double> entry : totalPerSomeSortingFactor.entrySet()) {  // Iterate through hash map
            Double percentage = ((entry.getValue() / total) * 100);
            percentagePerType.put(entry.getKey(), percentage);
        }

        return percentagePerType;
    }

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

    public static void calculateFinishDate(String email, double timeLeft, String taskId) {

        TaskEntryData data = new TaskEntryData();
        double averageTimePerEntry = data.getAverageOfTimeAdded(email); // ex. 25min
        double totalEntries = data.getTotalEntriesForTask(email, taskId); // ex. 10
        double numberOfDaysDifference = data.getDayDifferenceFromTaskStart(taskId); // ex. 14

        double averageEntriesPerDay = totalEntries / numberOfDaysDifference; // ex. 0.71 entries / day

        double numberOfEntriesRemaining = timeLeft / averageTimePerEntry; // ex. 75min left / 0.71 =

        double numberOfDays = numberOfEntriesRemaining / averageEntriesPerDay;

        // for day in days
        // if day is no work -> +1 day
        // if day is much work -> -1day

        // If task is new... get average of category/type/all
    }
}
