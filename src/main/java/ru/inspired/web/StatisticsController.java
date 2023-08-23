package ru.inspired.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class StatisticsController {

    @RequestMapping({"/"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        List<String> tmp = List.of("lorem", "ipsum");
        mv.addObject("stats", tmp);
        return mv;
    }
}
