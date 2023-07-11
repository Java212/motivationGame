package Notes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import Notes.model.Notes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/notes")

public class NotesController {

    public static List<Notes> notes = new ArrayList<>();

    @RequestMapping(method = RequestMethod.GET)
    public String viewNotes(Model model){
        model.addAttribute("notes",notes);
        return "notes";
    }

    @PostMapping
    public String addNote(String text){
        Notes note = new Notes();
        note.setText(text);
        note.setTime(LocalDateTime.now());
        notes.add(note);
        return "redirect:notes";
    }
}
