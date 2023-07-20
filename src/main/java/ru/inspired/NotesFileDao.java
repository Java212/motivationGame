package ru.inspired;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.inspired.model.Note;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        List<Note> Notes = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/test/resources/notes.txt"));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy.MM.dd HH:mm:ss");
            LocalDateTime dateTime = null;
            StringBuilder textBuilder = new StringBuilder();

            for (String line : lines) {
                try {
                    dateTime = LocalDateTime.parse(line, formatter);
                } catch (Exception e) {
                    String[] temp = line.split(" ");
                    if (temp.length > 0 && temp[0].equals("test")) {
                        textBuilder.append(line).append("\n");
                    }
                }

                if (dateTime != null && textBuilder.length() > 0) {
                    Note note = new Note(textBuilder.toString().trim(), dateTime);
                    Notes.add(note);

                    dateTime = null;
                    textBuilder.setLength(0);
                }
            }

            if (dateTime != null && textBuilder.length() > 0) {
                Note note = new Note(textBuilder.toString().trim(), dateTime);
                Notes.add(note);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Notes;
    }

    @Override
    public void addNote(Note note) {
        LOGGER.info("Writing a new note to the file repository");

    }
}
