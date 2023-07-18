package ru.inspired;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.inspired.model.Note;


import java.io.*;
import java.util.ArrayList;
import java.util.List;



/* file format
* line 1: datetime as dd.mm.yy hh:MM:ss
* line 2 .. N: long text of mote
* Line N+1: date again as marker of next note or end of file */
@Component
public class NotesFileDao implements NotesDao {

    public static final Logger LOGGER = LogManager.getLogger(NotesFileDao.class);


    @Override
    public List<Note> getNotes() {
       // LOGGER.info("Getting notes from the file repository");
        List<Note> list = new ArrayList<>();
        try  (BufferedReader reader = new BufferedReader(new FileReader(".\\src\\test\\resources\\loggs.txt"))) {
               String line;
                //String regex = "\\d+";
                int count=0;
                    while ((line = reader.readLine()) != null){
                        count++;
                        if (count%2==0) {
                            list.add(new Note(line));
                        }
                    }
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public void addNote(Note note) {
            String data = String.valueOf(note.getCreatedTime());
        try (BufferedWriter bufWr = new BufferedWriter(new FileWriter(".\\src\\test\\resources\\loggs.txt",  true))) {
            bufWr.write(data+"\n"+note.getText()+"\n");
            bufWr.flush();
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

}
