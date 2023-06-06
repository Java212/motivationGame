package ru.inspired;

import ru.inspired.model.MotivationEvent;

import java.util.List;

public interface MotivationEventDao {
    List<MotivationEvent> getMotivationEvents();
    MotivationEvent getEventById(int id);
}
