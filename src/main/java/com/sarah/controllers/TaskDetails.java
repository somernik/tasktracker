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
 * TaskDetails
 * Created by Sarah Omernik on 2/8/2017.
 */

@WebServlet(
        urlPatterns = {"/taskDetails"}
)
public class TaskDetails extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TaskData taskData = new TaskData();
        TaskEntryData taskEntryData = new TaskEntryData();
        HttpSession session = req.getSession();
        RequestDispatcher dispatcher;

        try {
            session.setAttribute("singleTask", taskData.getSingleTask(req.getParameter("id")));
            List<TaskEntry> entries = new ArrayList<TaskEntry>();
            entries = taskEntryData.getUserTaskEntries(req.getParameter("id"));

            List<Double> increasingEntries = getEntries(entries);
            session.setAttribute("plotPoints", increasingEntries);

            session.setAttribute("taskEntries", entries);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        dispatcher = req.getRequestDispatcher("/taskDetail.jsp");
        dispatcher.forward(req, resp);

    }

    /**
     * Adds up all task entries
     * @param entries the list of entries
     * @return the list of entries
     */
    private List<Double> getEntries(List<TaskEntry> entries) {
        List<Double> increasingEntries = new ArrayList<Double>();
        if (entries.size() > 0) {
            increasingEntries.add(entries.get(0).getTimeAdded());
            for (int index = 0; index < entries.size(); index++) {
                if (entries.size() > 1 && index > 0) {
                    increasingEntries.add(increasingEntries.get(index - 1) + entries.get(index).getTimeAdded());
                }
            }
        }
        return increasingEntries;
    }

}
