package ru.inspired.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.inspired.model.Note;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/notes")
public class NotesController {

    private final List<Note> notes;

    public NotesController() {
        notes = new ArrayList<>();
        notes.add(new Note("Created on startup for test"));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView viewNotes (){
        return getModelAndView();
    }

    private ModelAndView getModelAndView() {
        ModelAndView mv = new ModelAndView("notes");
        mv.addObject("notes", this.notes);
        return mv;
    }

    @PostMapping
    public ModelAndView addNote(String text){
        Note note = new Note(text);
        this.notes.add(note);
        return getModelAndView();
    }

}
