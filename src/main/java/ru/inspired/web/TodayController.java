package ru.inspired.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.inspired.DailyStatusDao;
import ru.inspired.MotivationEventDao;
import ru.inspired.MotivationScoreCalc;
import ru.inspired.model.*;

import java.util.LinkedList;
import java.util.List;

@Controller
public class TodayController {

    public static final Logger LOGGER = LogManager.getLogger(TodayController.class);

    @Autowired
    private MotivationEventDao motivationEventDao;

    @Autowired
    private DailyStatusDao dailyStatusDao;

    @RequestMapping(path = "/today", method = RequestMethod.GET)
    @PreAuthorize("authenticated")
    public ModelAndView getEventsForToday() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ( principal instanceof User)? ((User) principal) : new User(1);
        ModelAndView mv = new ModelAndView("markEvents");
        mv.addObject("events", motivationEventDao.getMotivationEvents(user));
        return mv;
    }

    @RequestMapping(path = "/today", method = RequestMethod.POST)
    @PreAuthorize("authenticated")
    public ModelAndView returnResults(@RequestParam(value = "boxes", required = false) int[] boxes) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ( principal instanceof User)? ((User) principal) : new User(1);

        ModelAndView mv = new ModelAndView("result");
        List<DailyStatus> list = readDailyStatuses(user.getId());
        MotivationScoreCalc calc = new MotivationScoreCalc(0);
        int initialBalance = calc.calculateScore(list);
        mv.addObject("yesterday", initialBalance);

        for (MotivationEvent event : motivationEventDao.getMotivationEvents(user)) {
            boolean found =  false;
            if(boxes != null) {
                for (int id : boxes) {
                    if (event.getId() == id) {
                        found = true;
                        break;
                    }
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

    private List<DailyStatus> readDailyStatuses(int userId) {
        List<DailyStatus> list = new LinkedList<>();
        try {
            list.addAll(dailyStatusDao.getDailyStatuses(userId));
        } catch (DataRelatedException e) {
            LOGGER.warn("Daily statuses are not read!");
        }
        return list;
    }
}
