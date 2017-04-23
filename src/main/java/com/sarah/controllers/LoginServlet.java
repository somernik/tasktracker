package com.sarah.controllers;

import com.sarah.entity.User;
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
 * LoginServlet
 * Creates session for user
 * Created by sarah on 2/25/2017.
 */
@WebServlet(
        urlPatterns = {"/LoginServlet"}
)
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserData userData = new UserData();
        boolean isValid = false;

        isValid = userData.validateUser(email, password);

        if(isValid){
            setUpUserSession(request, response, email, userData);
        }
        else{
            request.setAttribute("email", email);
            request.setAttribute("errorMessage","Sorry, username or password error!");
            request.getRequestDispatcher("/logIn.jsp").include(request, response);
        }

    }

    /**
     * Starts user session
     * @param request the request object
     * @param response the response object
     * @param email the users email
     * @param userData the users data
     * @throws IOException
     * @throws ServletException
     */
    private void setUpUserSession(HttpServletRequest request, HttpServletResponse response, String email, UserData userData) throws IOException, ServletException {
        TaskData taskData = new TaskData();
        HttpSession session = request.getSession();

        try {
            User user = userData.getUser(email);

            session.setAttribute("user", user);
            session.setAttribute("email", email);
            session.setAttribute("loggedIn", true);

            session.setAttribute("tasks", taskData.getUserTasks(session.getAttribute("email"), "active"));
        } catch (ErrorException exception) {
            request.setAttribute("message", exception.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard.jsp");
        dispatcher.forward(request, response);
    }
}