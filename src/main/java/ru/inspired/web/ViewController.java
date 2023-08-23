package ru.inspired.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {

    @RequestMapping("/menu")
    public String menu() {
        return "menu";
    }

    @RequestMapping({"/login"})
    public String login() {
        return "login";
    }

    @RequestMapping({"/login_error"})
    public ModelAndView loginError() {
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("error","Неправильное имя пользователя или пароль");
        return mv;
    }


}
