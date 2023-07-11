package ru.inspired;

import org.springframework.stereotype.Component;
import ru.inspired.model.Note;

import java.util.ArrayList;
import java.util.List;

@Component
public class NotesFileDao implements NotesDao{

    @Override
    public List<Note> getNotes() {
        return new ArrayList<>();
    }

    @Override
    public void addNote(Note note) {

    }
}
