package ru.inspired.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.inspired.NotesDao;
import ru.inspired.model.Note;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/notes")
public class NotesController {

    private final NotesDao notesDao;

    @Autowired
    public NotesController(NotesDao notesDao) {
        this.notesDao = notesDao;
        notesDao.addNote(new Note("Created on startup for test"));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView viewNotes() {
        return getModelAndView();
    }

    private ModelAndView getModelAndView() {
        ModelAndView mv = new ModelAndView("notes");
        mv.addObject("notes",
                notesDao.getNotes().stream().map(note -> {
                    return new NoteDto(note.getText(),
                            note.getCreatedTime().format(DateTimeFormatter.ISO_LOCAL_TIME));
                }).collect(Collectors.toList())
        );
        return mv;
    }

    @PostMapping
    public ModelAndView addNote(String text) {
        Note note = new Note(text);
        notesDao.addNote(note);
        return getModelAndView();
    }

}
