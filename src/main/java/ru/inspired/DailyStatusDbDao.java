package ru.inspired;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import ru.inspired.model.DailyStatus;
import ru.inspired.model.DataRelatedException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Profile("db")
public interface DailyStatusDbDao extends CrudRepository<DailyStatus, Integer>, DailyStatusDao {

    Logger LOGGER = LogManager.getLogger(DailyStatusDbDao.class);

    @Override
    default List<DailyStatus> getDailyStatuses(int userId) throws DataRelatedException {
        LOGGER.info("getting daily statuses");
        List<DailyStatus> result = new ArrayList<>();
        findAll().iterator().forEachRemaining(result::add);
        return result.stream().filter(dailyStatus -> dailyStatus.getMotivationEvent().getUser().getId()==userId).collect(Collectors.toList());
    }

    @Override
    default void saveDailyStatuses(List<DailyStatus> statuses) throws DataRelatedException {
        LOGGER.info("saving daily statuses");
        try {
            saveAll(statuses);
        } catch (DataIntegrityViolationException ex) {
            LOGGER.warn("Error saving daily statuses!" + ex.getMessage());
            throw new DataRelatedException(ex.getMessage());
        }
    }
}
