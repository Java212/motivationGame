package ru.inspired;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import ru.inspired.model.DataRelatedException;
import ru.inspired.model.MotivationEvent;
import ru.inspired.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Profile("db")
public interface MotivationEventDbDao extends CrudRepository<MotivationEvent, Integer>, MotivationEventDao {

    Logger LOGGER = LogManager.getLogger(MotivationEventDbDao.class);

    @Override
    default List<MotivationEvent> getMotivationEvents(User user) {
        LOGGER.info("getting daily statuses");
        List<MotivationEvent> result = new ArrayList<>();
        findAll().iterator().forEachRemaining(result::add);
        return result;
    }

    @Override
    default MotivationEvent getEventById(int id) {
        Optional<MotivationEvent> e = findById(id);
        return e.orElseThrow(DataRelatedException::new);
    }
}
