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
        HttpSession session = req.getSession();
        RequestDispatcher dispatcher;

        try {
            if (req.getParameter("submit").equals("editTask")) {
                session.setAttribute("singleTask", taskData.getSingleTask(req.getParameter("id")));

            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        dispatcher = req.getRequestDispatcher("/editTask.jsp");
        dispatcher.forward(req, resp);
    }
}
