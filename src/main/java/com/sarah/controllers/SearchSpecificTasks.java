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
        urlPatterns = {"/searchSpecificTasks"}
)
public class SearchSpecificTasks extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TaskData taskData = new TaskData();
        HttpSession session=request.getSession();
        String searchCriteria = "taskId = taskId";

        if (request.getParameter("submit").equals("searchInfo")){
            searchCriteria = determineSearchCriteria(request, searchCriteria);
        }

        session.setAttribute("tasks", taskData.getUserTasks(session.getAttribute("email"), searchCriteria));

        request.setAttribute("completion", request.getParameter("completion"));
        request.setAttribute("timeOperator", request.getParameter("timeOperator"));
        request.setAttribute("time", request.getParameter("timeSpent"));

        RequestDispatcher dispatcher = request.getRequestDispatcher("/searchTasks.jsp");
        dispatcher.forward(request, response);
    }

    private String determineSearchCriteria(HttpServletRequest request, String searchCriteria) {
        if (request.getParameter("completion").equals("completed") ) {
            searchCriteria += " AND completed = 1";
        } else if (request.getParameter("completion").equals("notCompleted") ) {
            searchCriteria += " AND completed = 0";
        }

        if (request.getParameter("timeOperator").equals("greaterThan") && (!request.getParameter("timeSpent").isEmpty())) {
            searchCriteria += " AND cumulativeTimeSpent >= " + request.getParameter("timeSpent");
        } else if (request.getParameter("timeOperator").equals("lessThan") && (!request.getParameter("timeSpent").isEmpty())) {
            searchCriteria += " AND cumulativeTimeSpent <= " + request.getParameter("timeSpent");
        }

        return searchCriteria;
    }
}
