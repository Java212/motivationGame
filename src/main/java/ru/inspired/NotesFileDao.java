package ru.inspired;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.inspired.model.Note;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* file format
* line 1: datetime as dd.mm.yy hh:MM:ss
* line 2 .. N: long text of mote
* Line N+1: date again as marker of next note or end of file */
@Component
public class NotesFileDao implements NotesDao{

    public static final Logger LOGGER = LogManager.getLogger(NotesFileDao.class);

    @Override
    public List<Note> getNotes() {
        LOGGER.info("Getting notes from the file repository");
        List<Note> notes = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("resources\\notes.csv"))) {
            String line;

            while ((line = in.readLine()) != null) {
                String[] lineSplit = line.split(",");
                notes.add(new Note(lineSplit[1]));
            }

        } catch (IOException | IndexOutOfBoundsException e) {

            throw new RuntimeException();

        }
        return notes;
    }

    @Override
    public void addNote(Note note) {
        LOGGER.info("Writing a new note to the file repository");

    }
}
