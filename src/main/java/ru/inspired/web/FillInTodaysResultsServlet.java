package ru.inspired.web;

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
import ru.inspired.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/today")
public class FillInTodaysResultsServlet extends HttpServlet {

    public static Logger LOGGER = LogManager.getLogger(FillInTodaysResultsServlet.class);

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {

        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            List<MotivationEvent> events = motivationEventDao.getMotivationEvents();

            out.println("<form method=\"post\" action=\"today\">");

            out.println("<h1> Заполняем результат за " + LocalDate.now() + "</h1>" + "\n <ul>");

            for(MotivationEvent event: events){
                out.println("<li>" + event.getDescription() + "<input type = \"checkbox\" name =\"" + event.getId() + "\"/></li>");
            }
            out.println("</ul>\n" + "    <input type=\"submit\"/>");
            out.println("</form>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String[] requestParams = sb.toString().split("&");

        LinkedList<DailyLog> list =  new LinkedList<>();
        try {
            list.addAll(logProcessor.getLog());
        } catch (IOException e) {
            LOGGER.warn("Motivation Events are not read!");
        }
        for(String pair : requestParams){
            String[] split = pair.split("=");
            MotivationEvent event = motivationEventDao.getEventById(Integer.parseInt(split[0]));
            CompletionStatus status = split[1].equals("on")?CompletionStatus.DONE:CompletionStatus.FAILED;
            DailyLog log = new DailyLog(event,status);
            list.add(log);
        }
        logProcessor.reWriteLog(list);
        MotivationScoreCalc calc = new MotivationScoreCalc(0);
        int balance = calc.calculateScore(list);
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {

            out.println("<h1> Получен результат за " + LocalDate.now() + "</h1>");
            out.println("<h2> Баланс:  " + balance + "</h2>");

        }

    }
}
