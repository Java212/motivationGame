package ru.inspired.notes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.List;

/* file format
* line 1: datetime as dd.MM.yy hh:mm:ss
* line 2 .. N: long text of mote
* Line N+1: date again as marker of next note or end of file */
@Component
@Profile("file")
public class NotesFileDao implements NotesDao{

    public static final Logger LOGGER = LogManager.getLogger(NotesFileDao.class);
    private static final String DATE_TIME_REGEX = "^([1-9]|([012][0-9])|(3[01]))\\.([0]{0,1}[1-9]|1[012])\\.\\d\\d (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d" ;

    @Override
    public List<Note> getNotes() {
        LOGGER.info("Getting notes from the file repository");
        return readNotesFromFile(this.getClass().getClassLoader().getResourceAsStream("notes.txt"));
    }

    private List<Note> readNotesFromFile(InputStream stream) {
        List<Note> list = new LinkedList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy hh:mm:ss");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            BufferedReader in = new BufferedReader(br);
            String line;
            boolean readNote = false;
            LocalDateTime dateTime = LocalDateTime.now();
            StringBuilder noteText = new StringBuilder();
            while ((line = in.readLine()) != null) {
                if(line.matches(DATE_TIME_REGEX) && readNote){
                    list.add(new Note(noteText.toString(),dateTime));
                    noteText = new StringBuilder();
                }
                if(line.matches(DATE_TIME_REGEX)){
                    dateTime = Instant.ofEpochMilli(dateFormat.parse(line.trim()).getTime())
                            .atZone(ZoneId.systemDefault())
                            .toLocalDateTime();
                    readNote = true;
                }
                if(readNote && !line.matches(DATE_TIME_REGEX)){
                    noteText.append(line).append("\n");
                }
            }
            list.add(new Note(noteText.toString(),dateTime));
        } catch (IOException e) {
            throw new RuntimeException ("Smth wrong in reading the file");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void addNote(Note note) {
        LOGGER.info("Writing a new note to the file repository");

    }
}
