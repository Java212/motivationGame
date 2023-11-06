package ru.inspired.notes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import ru.inspired.notes.dao.NotesDbDao;
import ru.inspired.notes.entity.Note;

import java.util.List;

@SpringBootTest
@ActiveProfiles(profiles = "db")
public class NotesDbDaoTest {

    public static final String HELLO_WORLD = "Hello, World!";
    @Autowired
    JdbcTemplate template;

    @Autowired
    NotesDbDao dao;

    @BeforeEach
    public void dropData() {

        template.execute("delete from notes");
    }

    @Test
    public void test_connectivity() {
        Assertions.assertDoesNotThrow(() -> dao.getNotes());
    }

    @Test
    public void test_insert_note() {
        Note note1 = new Note(HELLO_WORLD);
        Assertions.assertDoesNotThrow(() -> dao.addNote(note1));
    }

    @Test
    public void test_insert_and_get_note() {
        Note note1 = new Note(HELLO_WORLD);
        dao.addNote(note1);
        List<Note> list = dao.getNotes();
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(HELLO_WORLD, list.get(0).getText());
    }

    @Test
    public void test_insert_twice() {
        Note note1 = new Note(HELLO_WORLD);
        dao.addNote(note1);
        Note note2 = new Note(HELLO_WORLD);
        dao.addNote(note2);
        List<Note> list = dao.getNotes();
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(HELLO_WORLD, list.get(0).getText());
    }
}
