package ru.inspired.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.inspired.DailyStatusDao;
import ru.inspired.MotivationEventDao;
import ru.inspired.MotivationScoreCalc;
import ru.inspired.model.CompletionState;
import ru.inspired.model.DailyStatus;
import ru.inspired.model.DataRelatedException;
import ru.inspired.model.MotivationEvent;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Controller
public class TodayController {

    public static final Logger LOGGER = LogManager.getLogger(TodayController.class);

    @Autowired
    private MotivationEventDao motivationEventDao;

    @Autowired
    private DailyStatusDao dailyStatusDao;

    @RequestMapping({"/"})
    public String index() {
        return "index";
    }

    @RequestMapping(path = "/today", method = RequestMethod.GET)
    public ModelAndView getEventsForToday() {
        ModelAndView mv = new ModelAndView("markEvents");
        mv.addObject("events", motivationEventDao.getMotivationEvents());
        return mv;
    }

    @RequestMapping(path = "/today", method = RequestMethod.POST)
    public ModelAndView returnResults(@RequestBody(required = false) String payload) throws IOException {

        ModelAndView mv = new ModelAndView("result");
        List<DailyStatus> list = readDailyStatuses();
        MotivationScoreCalc calc = new MotivationScoreCalc(0);
        int initialBalance = calc.calculateScore(list);
        mv.addObject("yesterday", initialBalance);

        String[] requestParams = (payload == null || payload.isEmpty()) ? new String[0]
                : payload.split("&"); //1=on&2=on  , off  doesn't exist
        for (MotivationEvent event : motivationEventDao.getMotivationEvents()) {
            boolean found = false;
            for (String pair : requestParams) {
                String[] split = pair.split("=");
                if (event.getId() == Integer.parseInt(split[0])) {
                    found = true;
                }
            }
            CompletionState state = found ? CompletionState.DONE : CompletionState.FAILED;
            DailyStatus dailyStatus = new DailyStatus(event, state);
            list.add(dailyStatus);
        }

        dailyStatusDao.saveDailyStatuses(list);
        int balance = calc.calculateScore(list);
        mv.addObject("now", balance);
        return mv;
    }

    private List<DailyStatus> readDailyStatuses() {
        List<DailyStatus> list = new LinkedList<>();
        try {
            list.addAll(dailyStatusDao.getDailyStatuses());
        } catch (DataRelatedException e) {
            LOGGER.warn("Daily statuses are not read!");
        }
        return list;
    }
}
