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
 * Created by Sarah Omernik on 2/8/2017.
 */

@WebServlet(
        urlPatterns = {"/editTask"}
)
public class EditTask extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TaskData taskData = new TaskData();
        TaskEntryData taskEntryData = new TaskEntryData();
        HttpSession session = req.getSession();

        try {
            dispatchRequests(taskData, taskEntryData, session, req, resp);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    private void dispatchRequests(TaskData taskData, TaskEntryData taskEntryData, HttpSession session,
                                  HttpServletRequest req, HttpServletResponse resp)  throws Exception {
        RequestDispatcher dispatcher;

        if (req.getParameter("submit").equals("addTime")) {

            taskEntryData.addTime(req.getParameter("timeAdded"), req.getParameter("id"));
            taskData.updateTimeSpent(req.getParameter("id"));
            session.setAttribute("tasks", taskData.getUserTasks(session.getAttribute("email"), "taskId = taskId"));
            dispatcher = req.getRequestDispatcher("/dashboard.jsp");
            dispatcher.forward(req, resp);

        } else if (req.getParameter("submit").equals("details")) {
            session.setAttribute("singleTask", taskData.getSingleTask(req.getParameter("id")));
            List<TaskEntry> entries = new ArrayList<TaskEntry>();
            entries = taskEntryData.getUserTaskEntries(req.getParameter("id"));

            List<Double> increasingEntries = new ArrayList<Double>();
            if (entries.size() > 0) {
                increasingEntries.add(entries.get(0).getTimeAdded());
                for (int index = 0; index < entries.size(); index++) {
                    if (entries.size() > 1 && index > 0) {
                        increasingEntries.add(increasingEntries.get(index - 1) + entries.get(index).getTimeAdded());
                    }
                }
            }
            session.setAttribute("plotPoints", increasingEntries);

            session.setAttribute("taskEntries", entries);
            dispatcher = req.getRequestDispatcher("/taskDetail.jsp");
            dispatcher.forward(req, resp);

        } else if (req.getParameter("submit").equals("editTask")) {
            session.setAttribute("singleTask", taskData.getSingleTask(req.getParameter("id")));
            dispatcher = req.getRequestDispatcher("/editTask.jsp");
            dispatcher.forward(req, resp);

        } else if (req.getParameter("submit").equals("saveEdits")) {
            taskData.editSingleTask(req.getParameter("id"),
                    req.getParameter("taskName"), req.getParameter("taskCategory"), req.getParameter("taskType"),
                    req.getParameter("taskDescription"), req.getParameter("taskDueDate"), req.getParameter("completion"),
                    req.getParameter("taskStartDate"), req.getParameter("timeAdded"));
            session.setAttribute("singleTask", taskData.getSingleTask(req.getParameter("id")));
            session.setAttribute("taskEntries", taskEntryData.getUserTaskEntries(req.getParameter("id")));
            dispatcher = req.getRequestDispatcher("/taskDetail.jsp");
            dispatcher.forward(req, resp);

        } else if (req.getParameter("submit").equals("addEstimation")) {
            Task task = new Task();
            double estimate = Double.parseDouble(req.getParameter("estimation"));
            int id = Integer.parseInt(req.getParameter("id"));
            task.setEstimatedCompletionTime(estimate);
            task.setTaskId(id);
            taskData.updateEstimatedCompletionTime(task, estimate);

            session.setAttribute("tasks", taskData.getUserTasks(session.getAttribute("email"), "taskId = taskId"));
            dispatcher = req.getRequestDispatcher("/dashboard.jsp");
            dispatcher.forward(req, resp);
        }

    }
}
