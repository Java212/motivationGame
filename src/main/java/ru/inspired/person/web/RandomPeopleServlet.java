package ru.inspired.person.web;

import ru.inspired.person.model.Person;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


//@WebServlet("/motivationGame/randompeople")
//public class RandomPeopleServlet extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        List<Person> people = generateRandomPeople(10);
//
//        request.setAttribute("peopleList", people);
//
//        request.getRequestDispatcher("/randompeople.jsp").forward(request, response);
//    }
//
//    private List<Person> generateRandomPeople(int count) {
//        List<Person> people = new ArrayList<>();
//        Random random = new Random();
//
//        String[] names = {"Иван", "Алексей", "Елена", "Мария", "Андрей", "Ольга"};
//        String[] surnames = {"Иванов", "Петров", "Сидоров", "Кузнецов", "Смирнов", "Волков"};
//
//        for (int i = 0; i < count; i++) {
//            String name = names[random.nextInt(names.length)];
//            String surname = surnames[random.nextInt(surnames.length)];
//            String phoneNumber = generateRandomPhoneNumber();
//            people.add(new Person(name, surname, phoneNumber));
//        }
//
//        return people;
//    }
//
//    private String generateRandomPhoneNumber() {
//        Random random = new Random();
//        StringBuilder phoneNumber = new StringBuilder("+7");
//
//        for (int i = 0; i < 10; i++) {
//            phoneNumber.append(random.nextInt(10));
//        }
//
//        return phoneNumber.toString();
//    }
//}