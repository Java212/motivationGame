package ru.inspired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.inspired.model.MotivationEvent;

import java.util.List;

@Component
@Profile("db")
public class MotivationEventDbDao implements MotivationEventDao {

    public static final String SELECT_ALL = "select * from motivation_events";

    @Autowired
    JdbcTemplate template;

    @Override
    public List<MotivationEvent> getMotivationEvents() {
        return template.query(SELECT_ALL, (rs, rowNum) -> new MotivationEvent(rs.getInt("motivation_event_id"),
                rs.getString("text"), rs.getInt("bonus"), rs.getInt("fee")));
    }

    @Override
    public MotivationEvent getEventById(int id) {
        return template.query(SELECT_ALL, (rs, rowNum) -> new MotivationEvent(rs.getInt("motivation_event_id"),
                        rs.getString("text"), rs.getInt("bonus"), rs.getInt("fee"))).stream()
                .filter(e -> e.getId() == id).findFirst().orElseThrow();
    }
}
