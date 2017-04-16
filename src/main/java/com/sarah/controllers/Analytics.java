package com.sarah.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.*;

import com.sarah.entity.Task;
import com.sarah.persistence.Calculations;
import com.sarah.persistence.TaskData;

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

        List<Task> tasks = taskData.getUserTasks(email, searchCriteria);
        Map<String, Double> totalPerType = new HashMap<String, Double>();
        Map<String, Double> totalPerCategory = new HashMap<String, Double>();
        Double total = 0.0;

        timePerDayOfWeek = Calculations.dayOfWeekSetUp(timePerDayOfWeek);
        String mostCommonDay = Calculations.getMostCommonDay(timePerDayOfWeek);

        for (Task task : tasks) {
            total += task.getTimeSpent();
            timePerDayOfWeek = Calculations.calculateDaysOfWeek(task, timePerDayOfWeek);
            totalPerType = Calculations.updateTotalPerSomeSortingFactor(task, totalPerType, "type");
            totalPerCategory = Calculations.updateTotalPerSomeSortingFactor(task, totalPerCategory, "category");
            types.add(task.getTaskType());
        }

        percentagePerType = Calculations.calculatePercentages(percentagePerType, totalPerType, total);
        percentagePerCategory = Calculations.calculatePercentages(percentagePerCategory, totalPerCategory, total);

        // Set maps to be used
        request.setAttribute("mostCommonDay", mostCommonDay);
        request.setAttribute("types", types);
        request.setAttribute("timePerDay", timePerDayOfWeek);
        request.setAttribute("typePercentages", percentagePerType);
        request.setAttribute("categoryPercentages", percentagePerCategory);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/analytics.jsp");
        dispatcher.forward(request, response);
    }

}
