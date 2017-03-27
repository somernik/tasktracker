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
import java.util.List;
import java.util.*;

import com.sarah.entity.Task;
import com.sarah.persistence.TaskData;

/**
 * Created by Sarah Omernik on 3/22/2017.
 */

@WebServlet(
        urlPatterns = {"/analytics"}
)
public class Analytics extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TaskData taskData = new TaskData();
        HttpSession session=request.getSession();
        String searchCriteria = "taskId = taskId";
        List<Task> tasks;
        List<String> types = new ArrayList<String>();
        List<Double> value = new ArrayList<Double>();
        //Map<String, String> map = new HashMap<String, String>();

        //session.setAttribute("tasks", taskData.getUserTasks(session.getAttribute("email"), searchCriteria));
        tasks = taskData.getUserTasks(session.getAttribute("email"), searchCriteria);
        for (Task task : tasks) {
            types.add(task.getTaskType());
            value.add(task.getTimeSpent());

            task.getTaskCategory();
        }
        System.out.print(types);
        request.setAttribute("types", types);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/analytics.jsp");
        dispatcher.forward(request, response);
    }

}
