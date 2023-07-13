package ru.inspired;

import ru.inspired.model.MotivationEvent;

import java.util.LinkedList;
import java.util.List;

public class MotivationEventTestDao implements MotivationEventDao {
    @Override
    public List<MotivationEvent> getMotivationEvents() {
        return new LinkedList<>(); //как-то прочитали из фала список объектов MotivationEvent
    }

    @Override
    public MotivationEvent getEventById(int id) {
        return new MotivationEvent(id,"some task",1,0);
    }
}
