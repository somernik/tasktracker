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
import com.sarah.persistence.Calculations;
import com.sarah.persistence.TaskData;
import com.sarah.persistence.TaskEntryData;
import com.sarah.persistence.Utility;

/**
 * Analytics class
 * Created by Sarah Omernik on 3/22/2017.
 */

@WebServlet(
        urlPatterns = {"/analytics"}
)
public class Analytics extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoggedIn.checkLoggedIn(request, response);

        TaskData taskData = new TaskData();
        HttpSession session=request.getSession();
        String searchCriteria = "taskId = taskId";
        String email = (String) session.getAttribute("email");

        // Create maps to display on jsp
        List<String> types = new ArrayList<String>();
        Map<String, Double> timePerDayOfWeek = new HashMap<String, Double>();
        Map<String, Double> percentagePerType = new HashMap<String, Double>();
        Map<String, Double> percentagePerCategory = new HashMap<String, Double>();

        startCalculations(email, taskData, searchCriteria, types, timePerDayOfWeek, percentagePerType, percentagePerCategory);

        // Set maps to be used
        request.setAttribute("mostCommonDay", Calculations.getMostCommonDay(timePerDayOfWeek));
        request.setAttribute("types", types);
        request.setAttribute("timePerDay", timePerDayOfWeek);
        request.setAttribute("typePercentages", percentagePerType);
        request.setAttribute("categoryPercentages", percentagePerCategory);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/analytics.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Gathers needed informations and starts calculations
     * @param taskData the data about tasks
     * @param searchCriteria the criteria to search on
     * @param types the types of tasks
     * @param timePerDayOfWeek the map of days and time
     * @param percentagePerType the map of types and %s
     * @param percentagePerCategory the map of categories and %s
     */
    public static void startCalculations(String email, TaskData taskData, String searchCriteria, List<String> types,
                                         Map<String, Double> timePerDayOfWeek, Map<String, Double> percentagePerType,
                                         Map<String, Double> percentagePerCategory) {
        List<Task> tasks = taskData.getUserTasks(email, searchCriteria);
        Map<String, Double> totalPerType = new HashMap<String, Double>();
        Map<String, Double> totalPerCategory = new HashMap<String, Double>();
        Double total = 0.0;

        Calculations.dayOfWeekSetUp(timePerDayOfWeek);

        for (Task task : tasks) {
            total += task.getTimeSpent();
            Calculations.calculateDaysOfWeek(task, timePerDayOfWeek);
            Calculations.updateTotalPerSomeSortingFactor(task, totalPerType, "type");
            Calculations.updateTotalPerSomeSortingFactor(task, totalPerCategory, "category");
            types.add(task.getTaskType());
        }

        Calculations.calculatePercentages(percentagePerType, totalPerType, total);
        Calculations.calculatePercentages(percentagePerCategory, totalPerCategory, total);
    }

}
