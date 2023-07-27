package ru.inspired;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.inspired.model.Note;

import java.util.List;

@SpringBootTest
public class NotesDbDaoTest {

    public static final String HELLO_WORLD = "Hello, World!";
    @Autowired
    JdbcTemplate template;

    @Autowired
    NotesDbDao dao;

    @BeforeEach
    public void insertData() {
        template.execute("DROP TABLE IF EXISTS notes");
        template.execute("""
                CREATE TABLE notes
                (
                    id IDENTITY NOT NULL ,
                    text varchar NOT NULL,
                    date_time timestamp,
                    CONSTRAINT notes_pkey PRIMARY KEY (id)
                )""");
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
        dao.addNote(note1);
        List<Note> list = dao.getNotes();
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(HELLO_WORLD, list.get(0).getText());
    }


}
