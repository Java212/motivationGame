package ru.inspired.notes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@ExtendWith(SpringExtension.class)
class NotesControllerTest {


    @InjectMocks
    NotesController controller;

    @Mock
    NotesDao dao;


    @Test
    void testGet() throws Exception {

        Mockito.when(dao.getNotes()).thenReturn(
                List.of(new Note("new note 1"), new Note("new note 2 "))
        );

        ModelAndView mv = controller.viewNotes();
        Assertions.assertEquals("notes", mv.getViewName());
        List<Note> notes = (List) mv.getModelMap().get("notes");
        Assertions.assertEquals(2, notes.size());
    }

    @Test
    void testPostWithException() {

        Mockito.doThrow(RuntimeException.class).when(dao).addNote(Mockito.any(Note.class));
        Assertions.assertThrows(RuntimeException.class, () -> controller.addNote("some text"));

    }

}