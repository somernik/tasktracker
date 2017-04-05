package com.sarah.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.sarah.persistence.UserData;


/**
 * A simple servlet to add the user.
 * @author somernik
 */

@WebServlet(
        urlPatterns = {"/addUser"}
)

public class AddUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserData userData = new UserData();

        if (req.getParameter("submit").equals("addUser")) {
            userData.addNewUser(req.getParameter("firstName"), req.getParameter("lastName"), req.getParameter("username"),
                    req.getParameter("email"), req.getParameter("password"));
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/dashboard.jsp");
        dispatcher.forward(req, resp);
    }
}