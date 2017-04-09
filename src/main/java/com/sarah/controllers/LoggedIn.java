package com.sarah.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * LoggedIn class
 * checks if user is logged in
 * Created by sarah on 4/9/2017.
 */
public class LoggedIn {

    /**
     * Redirects user to login page, if they are not logged in.
     * @param request the httpservlet request
     * @param response the httpservlet response
     * @throws IOException for io exceptions
     * @throws ServletException for servlet exceptions
     */
    public static void checkLoggedIn(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session=request.getSession();

        if(session.getAttribute("loggedIn") == null) {
            response.setContentType("text/html");
            PrintWriter out=response.getWriter();
            out.print("<h2>Please login before accessing this page.</h2>");
            request.getRequestDispatcher("/logIn.jsp").include(request, response);
            out.close();
        }
    }
}
