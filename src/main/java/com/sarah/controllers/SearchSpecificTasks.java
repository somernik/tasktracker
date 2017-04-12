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
import java.util.Map;

import com.sarah.persistence.TaskData;

/**
 * SearchSpecificTasks
 * Created by Sarah Omernik on 2/8/2017.
 */

@WebServlet(
        urlPatterns = {"/searchSpecificTasks"}
)
public class SearchSpecificTasks extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoggedIn.checkLoggedIn(request, response);

        TaskData taskData = new TaskData();
        HttpSession session=request.getSession();
        String searchCriteria = "taskId = taskId";
        Map<String, String> types = taskData.getTypes((String) session.getAttribute("email"));

        if (request.getParameter("submit").equals("searchInfo")){
            searchCriteria = determineSearchCriteria(request, searchCriteria);
        }

        session.setAttribute("tasks", taskData.getUserTasks(session.getAttribute("email"), searchCriteria));
        request.setAttribute("types", types);
        request.setAttribute("completion", request.getParameter("completion"));
        request.setAttribute("timeOperator", request.getParameter("timeOperator"));
        request.setAttribute("time", request.getParameter("timeSpent"));

        RequestDispatcher dispatcher = request.getRequestDispatcher("/searchTasks.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Creates search criteria string
     * @param request the request object
     * @param searchCriteria the criteria string
     * @return searchCriteria
     */
    private String determineSearchCriteria(HttpServletRequest request, String searchCriteria) {

        switch (request.getParameter("completion")) {
            case "completed" : searchCriteria += " AND completed = 1";
            case "notCompleted" : searchCriteria += " AND completed = 0";
        }

        if (!request.getParameter("timeSpent").isEmpty()) {
            switch (request.getParameter("timeOperator")) {
                case "greaterThan" : searchCriteria += " AND cumulativeTimeSpent >= " + request.getParameter("timeSpent");
                    break;
                case "lessThan" : searchCriteria += " AND cumulativeTimeSpent <= " + request.getParameter("timeSpent");
                    break;
            }
        }

        if (!request.getParameter("type").equals("all")) {
            searchCriteria += " AND task.typeId='" + request.getParameter("type") + "'";
        }
        // TODO add category

        return searchCriteria;
    }
}
