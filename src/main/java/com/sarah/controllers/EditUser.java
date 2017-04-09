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
 * EditUser
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
        LoggedIn.checkLoggedIn(request, response);

        HttpSession session = request.getSession();
        UserData userData = new UserData();
        User user = (User) session.getAttribute("user");
        String oldEmail = user.getEmail();
        boolean isValid = false;

        isValid = userData.validateUser(oldEmail, request.getParameter("passwordOld"));

        if (isValid) {
            editUserValues(request, session, userData, oldEmail);
        } else {
            request.setAttribute("error", "Problem accessing information");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/profile.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Changes user values to entered values
     * @param request the request
     * @param session the current session
     * @param userData the user data object
     * @param oldEmail the current email
     */
    private void editUserValues(HttpServletRequest request, HttpSession session, UserData userData, String oldEmail) {
        User user;
        try {
            if (request.getParameter("password").equals(request.getParameter("passwordCheck")) && request.getParameter("password").length() > 0) {
                userData.editUser(request.getParameter("username"), request.getParameter("email"), request.getParameter("firstName"),
                        request.getParameter("lastName"), request.getParameter("password"), oldEmail);
                // TODO test how this works if changing email

            } else if (request.getParameter("password").equals(request.getParameter("passwordCheck")) && !(request.getParameter("password").length() > 0)) {
                // This wont write an empty password to the database
                userData.editUser(request.getParameter("username"), request.getParameter("email"), request.getParameter("firstName"),
                        request.getParameter("lastName"), request.getParameter("passwordOld"), oldEmail);
            }

            user = userData.getUser(request.getParameter("email"));
            session.setAttribute("user", user);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}