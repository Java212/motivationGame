package ru.inspired.person;

import ru.inspired.person.model.MotivationEvent;

import java.util.List;

public interface MotivationEventDao {
    List<MotivationEvent> getMotivationEvents(); // список событий
    MotivationEvent getEventById(int id); // получение событий по id
}
