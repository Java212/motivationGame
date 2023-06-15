package ru.inspired.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {

        response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.println("<h2>Hello from HelloServlet</h2>");
        }
        //request.getRequestDispatcher("index").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        resp.setContentType("application/json");
        try (PrintWriter writer = resp.getWriter()) {
            writer.println("{\"greeting\": \"hello\"}");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        resp.setContentType("application/json");
        try (PrintWriter writer = resp.getWriter()) {
            writer.println("{\"greeting\": \"hello, " + name +"\"}");
        }
    }
}
