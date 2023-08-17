package ru.inspired.notes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
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

    @Test
    void testNamedQuery() {
        LocalDateTime dateTime = LocalDateTime.of(2022,1,31,12,5);
        Note noteLastYear = new Note("text of old note", dateTime);
        dao.addNote(noteLastYear); //2022 31 jan

        LocalDateTime dateTime1 = LocalDateTime.of(2023,12,31,12,5);
        Note noteThisYear = new Note("text of new note", dateTime1);
        dao.addNote(noteThisYear); //2023 31 dec

        LocalDateTime dateTimeNow = LocalDateTime.of(2023,8,16,23,59);
        List<Note> notes = dao.getNotesFilteredByDate(dateTimeNow); //2023 16 aug

        Assertions.assertTrue(notes.contains(noteLastYear));
        Assertions.assertFalse(notes.contains(noteThisYear));

    }
}
