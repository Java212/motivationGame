package ru.inspired;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.inspired.model.MotivationEvent;

import java.util.List;

@Component
@Profile("db")
public class MotivationEventDbDao implements MotivationEventDao {

    public static final String SELECT_ALL = "select e from MotivationEvent e";

    @Autowired
    EntityManager entityManager;

    @Override
    public List<MotivationEvent> getMotivationEvents() {
        return entityManager.createQuery(SELECT_ALL, MotivationEvent.class).getResultList();
    }

    @Override
    public MotivationEvent getEventById(int id) {
        return new MotivationEvent(1,"test",1,2);
    }
}
