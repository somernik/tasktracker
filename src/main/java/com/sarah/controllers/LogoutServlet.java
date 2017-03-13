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
 * Created by sarah on 2/25/2017.
 */

@WebServlet(
        urlPatterns = {"/LoginServlet"}
)

public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        HttpSession session=request.getSession();
        session.invalidate();

        request.getRequestDispatcher("index.jsp").include(request, response);

        out.close();
    }
}