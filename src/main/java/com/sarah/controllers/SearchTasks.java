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
import java.util.Map;

import com.sarah.persistence.ErrorException;
import com.sarah.persistence.TaskData;

/**
 * SearchTasks
 * Created by Sarah Omernik on 2/8/2017.
 */
@WebServlet(
        urlPatterns = {"/searchTasks"}
)
public class SearchTasks extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoggedIn.checkLoggedIn(request, response);

        TaskData taskData = new TaskData();
        HttpSession session=request.getSession();
        String searchCriteria = "taskId = taskId";

       try {
           session.setAttribute("tasks", taskData.getUserTasks(session.getAttribute("email"), searchCriteria));

           Map<String, String> types = taskData.getTypes((String) session.getAttribute("email"));

           List<String> categories = taskData.getCategories((String) session.getAttribute("email"));
           request.setAttribute("categories", categories);

           request.setAttribute("types", types);

           RequestDispatcher dispatcher = request.getRequestDispatcher("/searchTasks.jsp");
           dispatcher.forward(request, response);

        } catch (ErrorException exception) {
            request.setAttribute("message", exception.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
        }
    }
}
