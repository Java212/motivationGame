package ru.inspired.notes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.inspired.notes.dao.NotesFileDao;
import ru.inspired.notes.entity.Note;

import java.util.List;


public class NotesFileDaoTest {
    @Test
    void readNotesFromFile() {
        NotesFileDao nfd = new NotesFileDao();
        List<Note> notes = nfd.getNotes();
        Assertions.assertEquals(2, notes.size());
    }
}
