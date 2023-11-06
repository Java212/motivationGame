package ru.inspired.notes.dao;

import ru.inspired.notes.entity.Note;

import java.util.List;

public interface NotesDao {
    List<Note> getNotes();

    void addNote(Note note);
}
