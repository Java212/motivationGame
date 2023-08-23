package ru.inspired.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.inspired.UserRepository;

@Controller
@RequestMapping("/user")
@Profile("db")
public class UserController {

    public static final Logger LOGGER = LogManager.getLogger(UserController.class);

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ModelAndView registerUser() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("message", "Регистрация прошла успешно, авторизируйтесь");
        return mv;
    }


}
