package ru.inspired;

import ru.inspired.model.MotivationEvent;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class MotivationEventSimpleDao implements MotivationEventDao{
    private final List<MotivationEvent> events;

    public MotivationEventSimpleDao() {
        events = new LinkedList<>();
        events.add(new MotivationEvent(1,"сделать что-то",5,5));
        events.add(new MotivationEvent(2,"сделать что-то еще полезное",10,0));
    }

    public MotivationEventSimpleDao(List<MotivationEvent> events) {
        this.events = events;
    }

    @Override
    public List<MotivationEvent> getMotivationEvents() {
        return events;
    }

    @Override
    public MotivationEvent getEventById(int id) throws NoSuchElementException {
        return events.stream().filter(e->e.getId() == id).findFirst().get();
    }
}
