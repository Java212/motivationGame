package ru.inspired;

import ru.inspired.model.MotivationEvent;

import java.util.List;

public interface MotivationEventDao {
    List<MotivationEvent> getMotivationEvents(); // список событий
    MotivationEvent getEventById(int id); // получение событий по id
}
