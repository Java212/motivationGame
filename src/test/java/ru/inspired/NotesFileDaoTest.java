package ru.inspired;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.inspired.model.Note;

import java.util.List;

public class NotesFileDaoTest {

    @Test
    void readNotesFromFile() {
        NotesFileDao nfd = new NotesFileDao();
        List<Note> notes = nfd.getNotes();
        Assertions.assertEquals(2, notes.size());
        
    }
}
