package ru.inspired;

import ru.inspired.model.MotivationEvent;
import ru.inspired.model.User;

import java.util.List;

public interface MotivationEventDao {
    List<MotivationEvent> getMotivationEvents(User user);

    MotivationEvent getEventById(int id);
}
