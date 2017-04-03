package com.sarah.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import com.sarah.entity.User;
import com.sarah.persistence.UserData;


/**
 * A simple servlet to edit the user.
 *
 * @author somernik 4/2/2017
 */

@WebServlet(
        urlPatterns = {"/editUser"}
)

public class EditUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserData userData = new UserData();
        User user = (User) session.getAttribute("user");
        String oldEmail = user.getEmail();

        try {
            if (request.getParameter("password").equals(request.getParameter("passwordCheck"))) {
                userData.editUser(request.getParameter("username"), request.getParameter("email"), request.getParameter("firstName"),
                        request.getParameter("lastName"), request.getParameter("password"), oldEmail);
                user = userData.getUser(request.getParameter("email"));
                session.setAttribute("user", user);

            }
        } catch (Exception exception)
        {
            exception.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/profile.jsp");
        dispatcher.forward(request, response);
    }
}