package ru.inspired;

import NoteWithDb.Note;
import NoteWithDb.NoteRepository;
import Notes.Application;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest(classes = Application.class)


public class NoteDbControllerTest {

    @Autowired
    NoteRepository noteDao;

    @Test
    void testAddNote(){
        Note note = new Note("hallo");
        noteDao.addNotes(note);
        Assertions.assertEquals(1, noteDao.getNotes().size());
    }

    @Test
    void test(){
        Note note1 = new Note("hallo");
        Note note2 = new Note("test");
        Note note3 = new Note("java");
        noteDao.addNotes(note1);
        noteDao.addNotes(note2);
        noteDao.addNotes(note3);
        List<Note> listNotes = noteDao.getNotes();
        Assertions.assertTrue(listNotes.contains(note2));
    }
}
