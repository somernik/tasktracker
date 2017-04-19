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
import java.util.Map;

import com.sarah.entity.Task;
import com.sarah.entity.TaskEntry;
import com.sarah.persistence.ErrorException;
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
        LoggedIn.checkLoggedIn(req, resp);

        try {
            getTaskInformation(req);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/editTask.jsp");
            dispatcher.forward(req, resp);

        } catch (ErrorException exception) {
            req.setAttribute("message", exception.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
            dispatcher.forward(req, resp);
        }

    }

    /**
     * Get Task information
     * @param req the request object
     * @throws ErrorException
     */
    private void getTaskInformation(HttpServletRequest req) throws ErrorException {
        TaskData taskData = new TaskData();
        HttpSession session = req.getSession();

        try {
            if (req.getParameter("submit").equals("editTask")) {
                Map<String, String> types = taskData.getTypes((String) session.getAttribute("email"));

                List<String> categories = taskData.getCategories((String) session.getAttribute("email"));
                req.setAttribute("categories", categories);
                req.setAttribute("types", types);

                session.setAttribute("singleTask", taskData.getSingleTask(req.getParameter("id")));

            }
        } catch (Exception exception) {
            throw new ErrorException();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
