package ru.inspired.file;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.inspired.MotivationEventDao;
import ru.inspired.model.MotivationEvent;

import java.util.List;
import java.util.NoSuchElementException;

@Component
@Profile("file")
public class MotivationEventSimpleDao implements MotivationEventDao {
    private final List<MotivationEvent> events;

    public MotivationEventSimpleDao() {
        this.events = List.of(new MotivationEvent(1, "сделать что-то полезное", 5, 0),
                new MotivationEvent(2, "сделать что-то менее полезное", 3, 0)
        );
    }

    @Override
    public List<MotivationEvent> getMotivationEvents() {
        return events;
    }

    @Override
    public MotivationEvent getEventById(int id) throws NoSuchElementException {
        return events.stream().filter(e -> e.getId() == id).findFirst().get();
    }
}
