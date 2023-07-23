package Notes;

import Notes.model.Notes;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(path = "/today", method = RequestMethod.GET)
    public ModelAndView getEventsForToday() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("notes", notes);
        return mv;
    }


    @PostMapping
    public String addNote(String text){
        Notes note = new Notes();
        note.setText(text);
        note.setTime(LocalDateTime.now());
        notes.add(note);
        return "redirect:index";
    }
}

