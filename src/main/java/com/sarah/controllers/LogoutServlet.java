package com.sarah.controllers;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

/**
 * LogoutServlet
 * Invalidates user's session to log them out
 * Created by sarah on 2/25/2017.
 */
@WebServlet(
        urlPatterns = {"/LogoutServlet"}
)
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LoggedIn.checkLoggedIn(request, response);

        HttpSession session=request.getSession();
        session.invalidate();

        String url = "/index.jsp";
        response.sendRedirect(url);
    }
}