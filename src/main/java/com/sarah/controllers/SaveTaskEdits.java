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
import com.sarah.persistence.TaskData;
import com.sarah.persistence.TaskEntryData;

/**
 * SaveTaskEdits
 * Created by Sarah Omernik on 2/8/2017.
 */

@WebServlet(
        urlPatterns = {"/saveTask"}
)
public class SaveTaskEdits extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoggedIn.checkLoggedIn(req, resp);

        // make in functions or new controllers
        try {
            dispatchRequests(req, resp);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    private void dispatchRequests(HttpServletRequest req, HttpServletResponse resp)  throws Exception {

        switch (req.getParameter("submit")) {
            case "addTime" : addTime(req, resp);
            case "saveEdits" : saveEdits(req, resp);
            case "addEstimation" : addEstimation(req, resp);
        }
    }

    private void addEstimation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        Task task = new Task();
        TaskData taskData = new TaskData();
        HttpSession session = req.getSession();

        double estimate = Double.parseDouble(req.getParameter("estimation"));
        int id = Integer.parseInt(req.getParameter("id"));
        task.setEstimatedCompletionTime(estimate);
        task.setTaskId(id);
        taskData.updateEstimatedCompletionTime(task, estimate);

        session.setAttribute("tasks", taskData.getUserTasks(session.getAttribute("email"), "taskId = taskId"));
        dispatcher = req.getRequestDispatcher("/dashboard.jsp");
        dispatcher.forward(req, resp);
    }

    private void saveEdits(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();
        TaskData taskData = new TaskData();
        TaskEntryData taskEntryData = new TaskEntryData();

        taskData.editSingleTask(req.getParameter("id"),
                req.getParameter("taskName"), req.getParameter("taskCategory"), req.getParameter("taskType"),
                req.getParameter("taskDescription"), req.getParameter("taskDueDate"), req.getParameter("completion"),
                req.getParameter("taskStartDate"), req.getParameter("timeAdded"));
        session.setAttribute("singleTask", taskData.getSingleTask(req.getParameter("id")));
        session.setAttribute("taskEntries", taskEntryData.getUserTaskEntries(req.getParameter("id")));
        dispatcher = req.getRequestDispatcher("/taskDetail.jsp");
        dispatcher.forward(req, resp);
    }

    private void addTime(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();
        TaskData taskData = new TaskData();
        TaskEntryData taskEntryData = new TaskEntryData();

        taskEntryData.addTime(req.getParameter("timeAdded"), req.getParameter("id"));
        taskData.updateTimeSpent(req.getParameter("id"));
        session.setAttribute("tasks", taskData.getUserTasks(session.getAttribute("email"), "taskId = taskId"));
        dispatcher = req.getRequestDispatcher("/dashboard.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
