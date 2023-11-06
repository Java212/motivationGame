package ru.inspired.notes.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.inspired.notes.entity.Note;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@Component
public class NotesFileDao implements NotesDao{

    public static final Logger LOGGER = LogManager.getLogger(NotesFileDao.class);
    private static final String DATE_TIME_REGEX = "^([1-9]|([012][0-9])|(3[01]))\\.([0]{0,1}[1-9]|1[012])\\.\\d\\d (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d" ;

    public static final String FILE_PATH = "src/main/resources/notes.txt";
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy hh:mm:ss");
        String timeToString = note.getCreatedTime().format(formatter);note.getCreatedTime();
        String text = note.getText();
        try (FileWriter fw = new FileWriter(FILE_PATH, true)){
            fw.write("\n" + timeToString);
            fw.write("\n" + text);
            LOGGER.info("Writing a new text");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
