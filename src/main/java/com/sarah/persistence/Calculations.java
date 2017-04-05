package com.sarah.persistence;

import com.sarah.entity.Task;
import com.sarah.entity.TaskEntry;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Calculations Class
 * Created by Sarah Omernik on 4/4/2017.
 */
public class Calculations {
    // TODO add javadoc
    /**
     * Gathers needed informations and starts calculations
     * @param taskData the data about tasks
     * @param searchCriteria the criteria to search on
     * @param types the types of tasks
     * @param timePerDayOfWeek the map of days and time
     * @param percentagePerType the map of types and %s
     * @param percentagePerCategory the map of categories and %s
     */
    public static void startCalculations(String email, TaskData taskData, String searchCriteria, List<String> types, Map<String, Double> timePerDayOfWeek, Map<String, Double> percentagePerType, Map<String, Double> percentagePerCategory) {
        List<Task> tasks = taskData.getUserTasks(email, searchCriteria);
        Map<String, Double> totalPerType = new HashMap<String, Double>();
        Map<String, Double> totalPerCategory = new HashMap<String, Double>();
        Double total = 0.0;

        dayOfWeekSetUp(timePerDayOfWeek);

        for (Task task : tasks) {
            total += task.getTimeSpent();
            calculateDaysOfWeek(task, timePerDayOfWeek);
            updateTotalPerSomeSortingFactor(task, totalPerType, "type");
            updateTotalPerSomeSortingFactor(task, totalPerCategory, "category");
            types.add(task.getTaskType());
        }

        calculatePercentages(percentagePerType, totalPerType, total);
        calculatePercentages(percentagePerCategory, totalPerCategory, total);
    }

    private static void dayOfWeekSetUp(Map<String, Double> timePerDayOfWeek) {
        Double starter = 0.0;
        timePerDayOfWeek.put("Monday", starter);
        timePerDayOfWeek.put("Tuesday", starter);
        timePerDayOfWeek.put("Wednesday", starter);
        timePerDayOfWeek.put("Thursday", starter);
        timePerDayOfWeek.put("Friday", starter);
        timePerDayOfWeek.put("Saturday", starter);
        timePerDayOfWeek.put("Sunday", starter);
    }

    private static void calculateDaysOfWeek(Task task, Map<String, Double> timePerDayOfWeek) {
        TaskEntryData taskEntryData = new TaskEntryData();
        List<TaskEntry> taskEntries = taskEntryData.getUserTaskEntries(String.valueOf(task.getTaskId()));
        for (TaskEntry entry : taskEntries) {
            String day = Utility.getDayFromLocalDate(entry.getDateEntered());
            addValuesToDayMap(timePerDayOfWeek, entry, day);
        }
    }

    private static void addValuesToDayMap(Map<String, Double> timePerDayOfWeek, TaskEntry entry, String day) {
        if (timePerDayOfWeek.containsKey(day)) {
            Double newTotal = timePerDayOfWeek.get(day).intValue() + entry.getTimeAdded();
            timePerDayOfWeek.put(day, newTotal);
        } else {
            timePerDayOfWeek.put(day, entry.getTimeAdded());
        }
    }

    private static void updateTotalPerSomeSortingFactor(Task task, Map<String, Double>totalPerSomeSortingFactor, String sortingFactor) {
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
    }

    private static void calculatePercentages(Map<String, Double>percentagePerType, Map<String, Double>totalPerSomeSortingFactor, Double total) {
        // Divide total per type by total, then * 100
        for (Map.Entry<String, Double> entry : totalPerSomeSortingFactor.entrySet()) {  // Iterate through hashmap
            Double percentage = ((entry.getValue() / total) * 100);
            percentagePerType.put(entry.getKey(), percentage);
        }
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


}
