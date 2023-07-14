package ru.inspired;

import ru.inspired.model.Note;

import java.util.List;

public interface NotesDao { //Data access object
    List<Note> getNotes();
    void addNote(Note note);
}
