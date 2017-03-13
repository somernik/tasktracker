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

import com.sarah.persistence.TaskData;

/**
 * Created by Sarah Omernik on 2/8/2017.
 */

@WebServlet(
        urlPatterns = {"/searchTasks"}
)
public class SearchTasks extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TaskData taskData = new TaskData();
        HttpSession session=request.getSession();
        String searchCriteria = "taskId = taskId";

        session.setAttribute("tasks", taskData.getUserTasks(session.getAttribute("email"), searchCriteria));

        RequestDispatcher dispatcher = request.getRequestDispatcher("/searchTasks.jsp");
        dispatcher.forward(request, response);
    }
}
