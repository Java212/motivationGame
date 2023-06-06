package ru.inspired;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.inspired.model.CompletionStatus;
import ru.inspired.model.DailyLog;
import ru.inspired.model.MotivationEvent;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MotivationEventsChecker {

    public static Logger LOGGER = LogManager.getLogger(MotivationEventsChecker.class);

    MotivationEventDao motivationEventDao;
    DailyLogProcessor logProcessor;
    MotivationScoreCalc calc;

    public MotivationEventsChecker(MotivationEventDao motivationEventDao,
                                   DailyLogProcessor logProcessor,
                                   MotivationScoreCalc calc) {
        this.motivationEventDao = motivationEventDao;
        this.logProcessor = logProcessor;
        this.calc = calc;
    }

    public void run(){
        PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        Scanner in = new Scanner(System.in);
        List<MotivationEvent> events = motivationEventDao.getMotivationEvents();
        LinkedList<DailyLog> list =  new LinkedList<>();
        try {
            list.addAll(logProcessor.getLog());
        } catch (IOException e) {
           LOGGER.warn("Motivation Events are not read!");
        }
        out.println("Баланс: " + calc.calculateScore(list));
        out.println("Заполняем результат за " + LocalDate.now());
        for(MotivationEvent event: events){
            out.println(event.getDescription());
            String result = in.next();
            CompletionStatus s = (result.equals("y"))? CompletionStatus.DONE : CompletionStatus.FAILED;
            DailyLog newLogEntry = new DailyLog(event,s);
            list.addLast(newLogEntry);
        }
        try {
            logProcessor.reWriteLog(list);
        } catch (IOException e) {
            LOGGER.warn("Motivation Events are not stored!");
        }
        int balance = calc.calculateScore(list);
        out.println("Баланс на конец дня: " + balance);

    }

}
