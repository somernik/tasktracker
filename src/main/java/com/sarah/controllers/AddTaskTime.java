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
import com.sarah.persistence.ErrorException;
import com.sarah.persistence.TaskData;
import com.sarah.persistence.TaskEntryData;

/**
 * SaveTaskEdits
 * Created by Sarah Omernik on 2/8/2017.
 */

@WebServlet(
        urlPatterns = {"/addTime"}
)
public class AddTaskTime extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoggedIn.checkLoggedIn(req, resp);

        // make in functions or new controllers
        try {
            HttpSession session = req.getSession();
            TaskData taskData = new TaskData();
            TaskEntryData taskEntryData = new TaskEntryData();

            taskEntryData.addTime(req.getParameter("timeAdded"), req.getParameter("id"));
            taskData.updateTimeSpent(req.getParameter("id"));
            session.setAttribute("tasks", taskData.getUserTasks(session.getAttribute("email"), "taskId = taskId"));

            RequestDispatcher dispatcher = req.getRequestDispatcher("/dashboard.jsp");
            dispatcher.forward(req, resp);

        } catch (ErrorException exception) {
            req.setAttribute("message", exception.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
            dispatcher.forward(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
