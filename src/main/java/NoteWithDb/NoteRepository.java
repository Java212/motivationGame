package NoteWithDb;

import java.util.List;

public interface NoteRepository {

    List<Note> getNotes();

    void addNotes(Note note);

    List<Note> filtrNoteById(int id);
}
