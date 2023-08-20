package Notes;

import NoteWithDb.Note;
import NoteWithDb.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


public class NoteDbController {
    @Autowired
    public NoteRepository noteDao;

    @RequestMapping({"/start"})
    public String index() {
        return "index";
    }

    @PostMapping("/index")
    public String addNote(String text){
        Note note = new Note(text);
        noteDao.addNotes(note);
        return "index";
    }

    @GetMapping("/notesThymeleaf")
    public ModelAndView viewote(){
        ModelAndView mv = new ModelAndView("listNotes");
        mv.addObject("notes", noteDao.getNotes());
        return mv;
    }
}
