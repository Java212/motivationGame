package ru.inspired.notes;

import java.util.List;

public interface NotesDao { //Data access object
    List<Note> getNotes();
    void addNote(Note note);
}
