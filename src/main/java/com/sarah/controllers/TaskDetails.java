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

        TaskData taskData = new TaskData();
        TaskEntryData taskEntryData = new TaskEntryData();
        HttpSession session = req.getSession();


        try {
            session.setAttribute("id", req.getParameter("id"));
            session.setAttribute("singleTask", taskData.getSingleTask((String) session.getAttribute("id")));
            List<TaskEntry> entries = new ArrayList<TaskEntry>();
            entries = taskEntryData.getUserTaskEntries(req.getParameter("id"));

            List<Double> increasingEntries = Calculations.getEntries(entries);
            session.setAttribute("plotPoints", increasingEntries);

            session.setAttribute("taskEntries", entries);

        } catch (ErrorException exception) {
            req.setAttribute("message", exception.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
            dispatcher.forward(req, resp);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/taskDetail.jsp");
        dispatcher.forward(req, resp);

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}