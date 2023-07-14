package ru.inspired.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.inspired.dao.PeopleDao;
import ru.inspired.model.People;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeopleService {
    @Autowired
    private PeopleDao peopleDao;

    @Autowired
    public List getAllPeople() {
        return peopleDao.getPeoples();
    }

}
