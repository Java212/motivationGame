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
@Test
    void addNotesFromFile() {
    Note note = new Note("Man macht etwas heute");
    NotesFileDao nfd = new NotesFileDao();
    nfd.addNote(note);
    Note note2 = new Note("Man geht schwimmen");
    nfd.addNote(note2);
    }
}
