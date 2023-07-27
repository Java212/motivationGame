package ru.inspired;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.inspired.model.Note;

import java.util.List;
import java.util.Optional;

@Component
@Primary
public class NotesDbDao implements NotesDao {

    public static final Logger LOGGER = LogManager.getLogger(NotesDbDao.class);
    private static final String SELECT_ALL = "select id, text, date_time from notes";
    private static final String INSERT = "insert into notes values (? , ? , ?)";
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public NotesDbDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Note> getNotes() {
        return jdbcTemplate.query(SELECT_ALL, (resultSet, i) -> new Note(
                resultSet.getString("text"),
                resultSet.getTimestamp("date_time").toLocalDateTime()));
    }

    @Override
    public void addNote(Note note) {
        try {
            Optional<Integer> max = jdbcTemplate.query("select max(id) as max from notes", (resultSet, i) -> resultSet.getInt(1)).stream().findFirst();
            int maxId = max.orElse(0);
            jdbcTemplate.update(INSERT, (maxId + 1), note.getText(), note.getCreatedTime());
        } catch (DataIntegrityViolationException ex){
            LOGGER.info("Data collision with notes");
            addNote(note); //retry
            // inform user
        }
    }
}