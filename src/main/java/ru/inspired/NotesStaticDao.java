package ru.inspired;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.inspired.model.Note;
import java.util.LinkedList;
import java.util.List;

@Component
public class NotesStaticDao implements NotesDao{
    public final List<Note> notesList = new LinkedList<>();
    public static final Logger LOGGER = LogManager.getLogger(NotesDbDao.class);

    public NotesStaticDao(){
        Note oneNote = new Note("Первая запись");
        this.notesList.add(oneNote);
    }
    @Override
    public List<Note> getNotes() {
        LOGGER.info("Getting notes from the static repository");
      return this.notesList;
    }

    @Override
    public void addNote(Note note) {
        this.notesList.add(note);

    }
}
