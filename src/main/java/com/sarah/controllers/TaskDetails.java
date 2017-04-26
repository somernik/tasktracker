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
import java.util.ArrayList;
import java.util.List;

import com.sarah.entity.Task;
import com.sarah.entity.TaskEntry;
import com.sarah.persistence.Calculations;
import com.sarah.persistence.ErrorException;
import com.sarah.persistence.TaskData;
import com.sarah.persistence.TaskEntryData;

/**
 * TaskDetails
 * Created by Sarah Omernik on 2/8/2017.
 */

@WebServlet(
        urlPatterns = {"/taskDetails"}
)
public class TaskDetails extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoggedIn.checkLoggedIn(req, resp);

        try {
            getTaskInformation(req, resp);

        } catch (ErrorException exception) {
            req.setAttribute("message", exception.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
            dispatcher.forward(req, resp);
        }

    }

    private void getTaskInformation(HttpServletRequest req, HttpServletResponse resp) throws ErrorException, ServletException, IOException {
        try {
            TaskData taskData = new TaskData();
            TaskEntryData taskEntryData = new TaskEntryData();
            HttpSession session = req.getSession();

            session.setAttribute("id", req.getParameter("id"));
            Task task = taskData.getSingleTask((String) session.getAttribute("id"));

            session.setAttribute("singleTask", task);
            List<TaskEntry> entries = taskEntryData.getUserTaskEntries(req.getParameter("id"));

            List<Double> increasingEntries = Calculations.getEntries(entries);
            double numberOfDaysToCompletion = Calculations.calculateFinishDate((String)session.getAttribute("email"), task.getTimeLeft(), task.getTaskId(), task.getTaskCategory(), task.getTaskType());

            session.setAttribute("plotPoints", increasingEntries);
            session.setAttribute("numberOfDays", numberOfDaysToCompletion);
            session.setAttribute("taskEntries", entries);


            RequestDispatcher dispatcher = req.getRequestDispatcher("/taskDetail.jsp");
            dispatcher.forward(req, resp);

        } catch (Exception exception) {
            String error = "getTaskInfo Exception: task " + exception;
            exception.printStackTrace();
            throw new ErrorException(error);

        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}