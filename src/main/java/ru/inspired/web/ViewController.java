package ru.inspired.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping("/menu")
    public String menu() {
        return "menu";
    }

    @RequestMapping({"/login", "/"})
    public String login() {
        return "login";
    }
}
