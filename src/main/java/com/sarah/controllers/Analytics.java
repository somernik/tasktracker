package com.sarah.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.Exception;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;
import java.util.Collections;

import com.sarah.entity.Task;
import com.sarah.entity.TaskEntry;
import com.sarah.persistence.TaskData;
import com.sarah.persistence.TaskEntryData;
import com.sarah.persistence.Utility;

/**
 * Created by Sarah Omernik on 3/22/2017.
 */

@WebServlet(
        urlPatterns = {"/analytics"}
)
public class Analytics extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TaskData taskData = new TaskData();
        HttpSession session=request.getSession();
        String searchCriteria = "taskId = taskId";
        List<Task> tasks;
        Map<String, Double> timePerDayOfWeek = new HashMap<String, Double>();
        Map<String, Double> totalPerType = new HashMap<String, Double>();
        Map<String, Double> percentagePerType = new HashMap<String, Double>();
        Double total = 0.0;

        List<String> types = new ArrayList<String>();
        List<Double> value = new ArrayList<Double>();

        //session.setAttribute("tasks", taskData.getUserTasks(session.getAttribute("email"), searchCriteria));
        tasks = taskData.getUserTasks(session.getAttribute("email"), searchCriteria);
        dayOfWeekSetUp(timePerDayOfWeek);

        for (Task task : tasks) {
            total += task.getTimeSpent();
            calculateDaysOfWeek(task, timePerDayOfWeek);
            updateTotalPerType(task, totalPerType);
            types.add(task.getTaskType());
            value.add(task.getTimeSpent());

            task.getTaskCategory();
        }

        calculatePercentagePerType(percentagePerType, totalPerType, total);

        request.setAttribute("mostCommonDay", getMostCommonDay(timePerDayOfWeek));
        request.setAttribute("types", types);
        request.setAttribute("timePerDay", timePerDayOfWeek);
        request.setAttribute("typePercentages", percentagePerType);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/analytics.jsp");
        dispatcher.forward(request, response);
    }
    private void dayOfWeekSetUp(Map<String, Double> timePerDayOfWeek) {
        Double starter = 0.0;
        timePerDayOfWeek.put("Monday", starter);
        timePerDayOfWeek.put("Tuesday", starter);
        timePerDayOfWeek.put("Wednesday", starter);
        timePerDayOfWeek.put("Thursday", starter);
        timePerDayOfWeek.put("Friday", starter);
        timePerDayOfWeek.put("Saturday", starter);
        timePerDayOfWeek.put("Sunday", starter);
    }
    private void calculateDaysOfWeek(Task task, Map<String, Double> timePerDayOfWeek) {
        Utility utility = new Utility();
        TaskEntryData taskEntryData = new TaskEntryData();
        List<TaskEntry> taskEntries = taskEntryData.getUserTaskEntries(String.valueOf(task.getTaskId()));
        for (TaskEntry entry : taskEntries) {
            String day = utility.getDayFromLocalDate(entry.getDateEntered());
            if (timePerDayOfWeek.containsKey(day)) {
                Double newTotal = new Double(timePerDayOfWeek.get(day).intValue() + entry.getTimeAdded());
                timePerDayOfWeek.put(day, newTotal);
            } else {
                timePerDayOfWeek.put(day, new Double(entry.getTimeAdded()));
            }
        }
    }
    private void updateTotalPerType(Task task, Map<String, Double>totalPerType) {

        if (totalPerType.containsKey(task.getTaskType())) {
            Double newTotal = totalPerType.get(task.getTaskType()).intValue() + task.getTimeSpent();
            totalPerType.put(task.getTaskType(), newTotal);
        } else {
            totalPerType.put(task.getTaskType(), (task.getTimeSpent()));
        }
    }

    private void calculatePercentagePerType(Map<String, Double>percentagePerType, Map<String, Double>totalPerType, Double total) {
        // Divide total per type by total, then * 100
        for (Map.Entry<String, Double> entry : totalPerType.entrySet()) {  // Iterate through hashmap
            Double percentage = ((entry.getValue() / total) * 100);
            percentagePerType.put(entry.getKey(), percentage);
        }
    }

    private String getMostCommonDay(Map<String, Double> timePerDayOfWeek) {
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
