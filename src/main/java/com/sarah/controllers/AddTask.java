package com.sarah.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Exception;
import java.util.*;

import com.sarah.entity.Task;
import com.sarah.persistence.TaskData;

/**
 * AddTask
 * adds new task
 * Created by Sarah Omernik on 2/8/2017.
 */
@WebServlet(
        urlPatterns = {"/addTask"}
)
public class AddTask extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LoggedIn.checkLoggedIn(request, response);

        TaskData taskData = new TaskData();
        HttpSession session=request.getSession();
        String type = request.getParameter("type");

        if (request.getParameter("submit").equals("addTask")) {
            try {
                if (type.equals("new")) {

                    // add type & get type id
                    type = taskData.addType(request.getParameter("newType"), (String)session.getAttribute("email"));
                }
                System.out.print(type);
                taskData.addNewTask(request.getParameter("taskName"), request.getParameter("taskCategory"), type,
                        request.getParameter("taskDescription"), request.getParameter("taskDueDate"), session.getAttribute("email"));
                System.out.print(type);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            System.out.print(type);
            session.setAttribute("tasks", taskData.getUserTasks(session.getAttribute("email"), "completed=0"));

            RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard.jsp");
            dispatcher.forward(request, response);

        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoggedIn.checkLoggedIn(request, response);

        TaskData taskData = new TaskData();
        HttpSession session=request.getSession();

        Map<String, String> types;
        types = taskData.getTypes((String) session.getAttribute("email"));

        request.setAttribute("types", types);

        session.setAttribute("tasks", taskData.getUserTasks(session.getAttribute("email"), "completed=0"));

        RequestDispatcher dispatcher = request.getRequestDispatcher("/addTask.jsp");
        dispatcher.forward(request, response);

    }
}
