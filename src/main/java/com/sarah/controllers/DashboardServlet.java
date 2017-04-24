package com.sarah.controllers;

import com.sarah.persistence.ErrorException;
import com.sarah.persistence.TaskData;
import com.sarah.persistence.UserData;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

/**
 * Dashboard Servlet
 * Shows users dashboard
 * Created by sarah on 2/25/2017.
 */
@WebServlet(
        urlPatterns = {"/dashboard"}
)
public class DashboardServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        LoggedIn.checkLoggedIn(request, response);

        TaskData taskData = new TaskData();
        try {
            session.setAttribute("tasks", taskData.getUserTasks(session.getAttribute("email"), "active"));
            request.getRequestDispatcher("/dashboard.jsp").include(request, response);

        } catch (ErrorException exception) {
            request.setAttribute("message", exception.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
        }


    }
}

