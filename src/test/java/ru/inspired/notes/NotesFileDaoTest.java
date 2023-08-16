package ru.inspired.notes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.inspired.notes.Note;
import ru.inspired.notes.NotesFileDao;

import java.util.List;

public class NotesFileDaoTest {

    @Test
    void readNotesFromFile() {
        NotesFileDao nfd = new NotesFileDao();
        List<Note> notes = nfd.getNotes();
        Assertions.assertEquals(2, notes.size());
    }
}
