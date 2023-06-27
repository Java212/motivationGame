package ru.inspired.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet ("/table")
public class TableServet extends HttpServlet{

@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        ListUser data = new ListUser();
            List<User> list = data.getList();

                request.setAttribute("data", list);
                     request.getRequestDispatcher("table.jsp").forward(request, response);
    }
}
