package ru.inspired;

import ru.inspired.model.MotivationEvent;

import java.util.LinkedList;
import java.util.List;

public class MotivationEventFileDao implements MotivationEventDao {
    @Override
    public List<MotivationEvent> getMotivationEvents() {
        return new LinkedList<>(); //как-то прочитали из файла список объектов MotivationEvent
    }

    @Override
    public MotivationEvent getEventById(int id) {
        return new MotivationEvent(id,"some task",1,0);
    }
}
