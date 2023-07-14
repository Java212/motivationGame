package ru.inspired.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.inspired.model.People;
import ru.inspired.service.PeopleService;

import java.util.List;

@Controller
@RequestMapping("/RandomPl")
public class RandomPeopleController {
    @Autowired
    private PeopleService peopleService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody
    List<People> getAllPeople() {
        return peopleService.getAllPeople();
    }

}
