package ru.inspired;

import ru.inspired.model.MotivationEvent;

import java.util.List;
import java.util.NoSuchElementException;

public class MotivationEventSimpleDao implements MotivationEventDao{
    List<MotivationEvent> events;

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
