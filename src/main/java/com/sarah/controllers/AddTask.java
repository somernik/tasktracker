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
        TaskData taskData = new TaskData();
        HttpSession session=request.getSession();

        if (request.getParameter("submit").equals("addTask")) {
            try {
                Task task = new Task();
                taskData.addNewTask(request.getParameter("taskName"), request.getParameter("taskCategory"), request.getParameter("taskType"),
                        request.getParameter("taskDescription"), request.getParameter("taskDueDate"), session.getAttribute("email"));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        session.setAttribute("tasks", taskData.getUserTasks(session.getAttribute("email"), "completed=0"));

        RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
