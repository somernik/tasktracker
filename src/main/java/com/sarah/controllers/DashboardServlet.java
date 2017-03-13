package com.sarah.controllers;

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
        urlPatterns = {"/dashboard"}
)
public class DashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        TaskData taskData = new TaskData();
        HttpSession session=request.getSession();

        if(session!=null){
            session.setAttribute("tasks", taskData.getUserTasks(session.getAttribute("email"), "active"));
            request.getRequestDispatcher("/dashboard.jsp").include(request, response);
        }
        else{
            out.print("Please login first");
            request.getRequestDispatcher("/logIn.jsp").include(request, response);
            out.close();
        }

    }
}

