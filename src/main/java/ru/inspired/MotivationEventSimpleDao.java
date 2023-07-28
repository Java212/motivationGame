package ru.inspired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.inspired.model.MotivationEvent;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class MotivationEventSimpleDao implements MotivationEventDao{
    private final List<MotivationEvent> events;

    @Autowired
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
