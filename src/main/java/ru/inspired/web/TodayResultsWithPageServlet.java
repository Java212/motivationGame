package ru.inspired.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import ru.inspired.DailyLogProcessor;
import ru.inspired.MotivationEventDao;
import ru.inspired.MotivationScoreCalc;

import ru.inspired.model.DailyLog;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/today/jsp")
public class TodayResultsWithPageServlet extends HttpServlet {

    public static Logger LOGGER = LogManager.getLogger(TodayResultsWithPageServlet.class);

    private  MotivationEventDao motivationEventDao;
    private  DailyLogProcessor logProcessor;

    @Override
    public void init()  {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("config.xml"));

        motivationEventDao = factory.getBean("motivationEventDao", MotivationEventDao.class);
        logProcessor = factory.getBean("dailyLogProcessor", DailyLogProcessor.class);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String destination = "/markEvents.jsp";
        request.setAttribute("events", motivationEventDao.getMotivationEvents());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        List<DailyLog> list = new LinkedList<>();
        try {
            list.addAll(logProcessor.getLog());
        } catch (IOException e) {
            LOGGER.warn("Motivation Events are not read!");
        }
        MotivationScoreCalc calc = new MotivationScoreCalc(0);
        String initialBalance = "Баланс на вчера: " + calc.calculateScore(list);
//        String[] requestParams = sb.toString().split("&"); //1=on&2=on  , off  doesn't exist
//        for (MotivationEvent event : motivationEventDao.getMotivationEvents()) {
//            boolean found = false;
//            for (String pair : requestParams) {
//                String[] split = pair.split("=");
//                if (event.getId() == Integer.parseInt(split[0])) {
//                    found = true;
//                }
//            }
//            CompletionStatus status = found ? CompletionStatus.DONE : CompletionStatus.FAILED;
//            DailyLog log = new DailyLog(event, status);
//            list.add(log);
//        }
//        logProcessor.reWriteLog(list);
//        int balance = calc.calculateScore(list);
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            out.println("<h1> Получен результат за " + LocalDate.now() + "</h1>");
            out.println("<h2> " + initialBalance + "</h2>");
            //out.println("<h2> Баланс: " + balance + "</h2>");
        }
    }
}
