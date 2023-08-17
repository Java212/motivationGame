package ru.inspired;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.inspired.model.CompletionState;
import ru.inspired.model.DailyStatus;
import ru.inspired.model.DataRelatedException;

import java.sql.Date;
import java.util.List;

@Component
@Profile("db")
public class DailyStatusDbDao implements DailyStatusDao {

    public static final Logger LOGGER = LogManager.getLogger(DailyStatusDbDao.class);

    private final JdbcTemplate template;

    private static final String SELECT_ALL = "select date_of_check, motivation_event_id, completion_state, calculated_score from daily_statuses";

    private static final String INSERT_QUERY = "insert into daily_statuses(motivation_event_id,completion_state, calculated_score) values(?,?,?)";

    @Autowired
    public DailyStatusDbDao(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<DailyStatus> getDailyStatuses() throws DataRelatedException {
        LOGGER.info("getting daily statuses");
        return template.query(SELECT_ALL, (rs, rowNum) -> {
            CompletionState state = CompletionState.valueOf(rs.getString("completion_state"));
            Date date = rs.getDate("date_of_check");
            return new DailyStatus(date.toLocalDate(), rs.getInt("motivation_event_id"), state, rs.getInt("calculated_score"));
        });
    }

    @Override
    public void saveDailyStatuses(List<DailyStatus> statuses) throws DataRelatedException {
        LOGGER.info("saving daily statuses");
        try {
            for (DailyStatus s : statuses) {
                template.update(INSERT_QUERY, s.getMotivationEventId(), s.getStatus().name(), s.getCalculationScore());
            }
        } catch (DataIntegrityViolationException ex) {
            LOGGER.warn("Error saving daily statuses!" + ex.getMessage());
            throw new DataRelatedException(ex.getMessage());
        }
    }
}
