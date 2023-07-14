package ru.inspired.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.inspired.model.People;



import java.util.Arrays;
import java.util.List;

@Repository
public class PeopleDao {
    private List<People> peoples = Arrays.asList(
            new People("Сергей Иванов","123-345-567"),
            new People("Марк Петров", "765-543-321"));
@Autowired
    public List<People> getPeoples(){

        return peoples;
    }


}
