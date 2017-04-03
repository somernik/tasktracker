package com.sarah.controllers;

import com.sarah.entity.User;
import com.sarah.persistence.TaskData;
import com.sarah.persistence.UserData;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

/**
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
        TaskData taskData = new TaskData();
        UserData userData = new UserData();
        boolean isValid = false;

        isValid = userData.validateUser(email, password);

        //if(isValid){
            HttpSession session = request.getSession();
            User user = userData.getUser(email);
            session.setAttribute("user", user);
            //session.setAttribute("email", email);
            session.setAttribute("email", "somernik@madisoncollege.edu");


            session.setAttribute("loggedIn", true);
            session.setAttribute("tasks", taskData.getUserTasks(session.getAttribute("email"), "active"));
            //request.getRequestDispatcher("/dashboard.jsp").include(request, response);
            String url = "/dashboard.jsp";
            response.sendRedirect(url);
        /*}
        else{
            request.setAttribute("email", email);
            request.setAttribute("errorMessage","Sorry, username or password error!");
            request.getRequestDispatcher("/logIn.jsp").include(request, response);
        }*/

    }
}