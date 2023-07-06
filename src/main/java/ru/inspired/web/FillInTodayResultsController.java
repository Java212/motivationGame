package ru.inspired.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.inspired.DailyLogProcessor;
import ru.inspired.MotivationEventDao;
import ru.inspired.MotivationScoreCalc;
import ru.inspired.model.CompletionStatus;
import ru.inspired.model.DailyLog;
import ru.inspired.model.MotivationEvent;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Controller
public class FillInTodayResultsController {

    public static Logger LOGGER = LogManager.getLogger(FillInTodayResultsController.class);

    @Autowired
    private MotivationEventDao motivationEventDao;

    @Autowired
    private DailyLogProcessor logProcessor;

    @RequestMapping(path = "/today", method = RequestMethod.GET)
    public ModelAndView getEventsForToday() {
        ModelAndView mv = new ModelAndView("markEvents");
        mv.addObject("events", motivationEventDao.getMotivationEvents());
        return mv;
    }

    @RequestMapping(path = "/today", method = RequestMethod.POST)
    public ModelAndView returnResults(@RequestBody(required = false) String payload) throws IOException {

        ModelAndView mv = new ModelAndView("result");
        List<DailyLog> list = readDailyLogFile();
        MotivationScoreCalc calc = new MotivationScoreCalc(0);
        int initialBalance = calc.calculateScore(list);
        mv.addObject("yesterday", initialBalance);

        String[] requestParams = (payload==null || payload.isEmpty())? new String[0]
                :payload.split("&"); //1=on&2=on  , off  doesn't exist
        for (MotivationEvent event : motivationEventDao.getMotivationEvents()) {
            boolean found = false;
            for (String pair : requestParams) {
                String[] split = pair.split("=");
                if (event.getId() == Integer.parseInt(split[0])) {
                    found = true;
                }
            }
            CompletionStatus status = found ? CompletionStatus.DONE : CompletionStatus.FAILED;
            DailyLog log = new DailyLog(event, status);
            list.add(log);
        }

        logProcessor.reWriteLog(list);
        int balance = calc.calculateScore(list);
        mv.addObject("now", balance);
        return mv;
    }

    private List<DailyLog> readDailyLogFile() {
        List<DailyLog> list = new LinkedList<>();
        try {
            list.addAll(logProcessor.getLog());
        } catch (IOException e) {
            LOGGER.warn("Motivation Events are not read!");
        }
        return list;
    }
}
