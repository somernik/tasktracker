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
        urlPatterns = {"/saveTask"}
)
public class SaveTaskEdits extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoggedIn.checkLoggedIn(req, resp);

        // make in functions or new controllers
        try {
            RequestDispatcher dispatcher;
            HttpSession session = req.getSession();
            TaskData taskData = new TaskData();
            TaskEntryData taskEntryData = new TaskEntryData();

            String type = req.getParameter("type");
            String category = req.getParameter("taskCategory");


            if (type.equals("new")) {

                // add type & get type id
                type = taskData.addType(req.getParameter("newType"), (String) session.getAttribute("email"));
            }

            if (category.equals("new")) {
                category = req.getParameter("newCategory");
            }


            taskData.editSingleTask(req.getParameter("id"),
                    req.getParameter("taskName"), category, type,
                    req.getParameter("taskDescription"), req.getParameter("taskDueDate"), req.getParameter("completion"),
                    req.getParameter("taskStartDate"), req.getParameter("timeAdded"));

            session.setAttribute("singleTask", taskData.getSingleTask(req.getParameter("id")));
            session.setAttribute("taskEntries", taskEntryData.getUserTaskEntries(req.getParameter("id")));
            dispatcher = req.getRequestDispatcher("/taskDetail.jsp");
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
