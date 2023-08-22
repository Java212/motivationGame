package ru.inspired;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.inspired.model.MotivationEvent;

import java.util.List;

@Component
@NamedQueries(
        @NamedQuery(name="selectAllEvents", query = "select n from n motivationevents")
)
public class MotivationEventDbDao implements MotivationEventDao {
    @Autowired
    EntityManager entityManager;

    @Override
    public List<MotivationEvent> getMotivationEvents(){
        return entityManager.createNamedQuery("selectAllEvents").getResultList();
    }

    @Override
   public MotivationEvent getEventById(int id){
        return entityManager.find(MotivationEvent.class, id);
    }
}
