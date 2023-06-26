package ru.inspired.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;


@WebServlet("/randomPeople")
public class RandomPeopleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String[] names = new String[]{"Сергей", "Марк", "Боб", "Олег", "Иван", "Денис", "Игорь", "Даниил", "Кирилл", "Алекс"};
        String[] surname = new String[]{"Иванов", "Петров", "Сидоров", "Попов", "Колосов", "Гагарин", "Циолковский", "Ленин", "Сталин", "Столыпин"};

        List<String> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int n = (int) Math.floor(Math.random() * names.length);
            users.add(names[n] + " " + surname[n]);


        }


        request.setAttribute("users", users);
        request.getRequestDispatcher("randompeople.jsp").forward(request, response);
    }


}
