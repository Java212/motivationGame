package Notes;

import Notes.model.Notes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ControllerThymeleaf {
    public static List<Notes> notes = new ArrayList<>();

    @RequestMapping({"/"})
        public String index() {
        return "index";
    }

    @RequestMapping(path="/index", method = RequestMethod.GET)
    public String viewNotes(Model model){
        model.addAttribute("notes",notes);
        return "index";
    }

    @PostMapping ("/index")
    public String addNote(String text){
        Notes note = new Notes();
        note.setText(text);
        note.setTime(LocalDateTime.now());
        notes.add(note);
        return "index";
    }

    @GetMapping("/notesThymeleaf")
    public ModelAndView viewote(){
        ModelAndView mv = new ModelAndView("listNotes");
        mv.addObject("notes", notes);
        return mv;
    }
}
