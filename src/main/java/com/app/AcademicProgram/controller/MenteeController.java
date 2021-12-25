package com.app.AcademicProgram.controller;

import com.app.AcademicProgram.model.Mentee;
import com.app.AcademicProgram.model.Note;
import com.app.AcademicProgram.service.MenteeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The type Mentee controller.
 */
@Controller
@RequestMapping("/mentees")
public class MenteeController {
    /**
     * The Mentee service.
     */
    MenteeService menteeService;

    /**
     * Instantiates a new Mentee controller.
     *
     * @param menteeService the mentee service
     */
    @Autowired
    public MenteeController(MenteeService menteeService) {
        this.menteeService = menteeService;
    }

    /**
     * Mentees homepage string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/list")
    public String menteesHomepage(Model model) {
        model.addAttribute("menteesList", menteeService.menteesList());
        return "mentees";
    }

    /**
     * Mentees notes string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/notes/{id}")
    public String menteesNotes(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("mentee", menteeService.findMenteeById(id));
        model.addAttribute("menteeNotes", menteeService.menteeNotes(id));
        return "notes";
    }

    /**
     * Show form string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/create")
    public String showForm(Model model) {
        model.addAttribute("mentee", new Mentee());
        return "menteeform";
    }

    /**
     * Check mentee info string.
     *
     * @param menteeForm    the mentee form
     * @param bindingResult the binding result
     * @return the string
     */
    @PostMapping("/create")
    public String checkMenteeInfo(@Valid Mentee menteeForm, BindingResult bindingResult) {
        Mentee mentee = menteeService.findMenteeByEmail(menteeForm.getEmail());
        if (mentee != null) {
            bindingResult.rejectValue("email", null, "There is already an mentee account registered with that email");
        }
        Mentee menteeByRollNumber = menteeService.findMenteeByRollNumber(menteeForm.getRollNumber());
        if (menteeByRollNumber!=null) {
            bindingResult.rejectValue("rollNumber", null, "There is already a mentee with same roll number");
        }

        if (bindingResult.hasErrors()) {
            return "menteeform";
        } else {
            menteeService.saveMentee(menteeForm);
            return "redirect:/mentees/list";
        }
    }

    /**
     * Show form for update string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/update/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Integer id, Model model) {
        Mentee mentee = menteeService.findMenteeById(id);
        model.addAttribute("mentee", mentee);
        return "updatementeeform";
    }

    /**
     * Update mentee string.
     *
     * @param mentee        the mentee
     * @param bindingResult the binding result
     * @return the string
     */
    @PostMapping("/update")
    public String updateMentee(@Valid Mentee mentee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "updatementeeform";
        } else {
            menteeService.saveMentee(mentee);
            return "redirect:/mentees/list";
        }
    }

    /**
     * Delete mentee string.
     *
     * @param id the id
     * @return the string
     */
    @GetMapping("/delete/{id}")
    public String deleteMentee(@PathVariable(value = "id") Integer id) {
        menteeService.deleteMentee(id);
        return "redirect:/mentees/list";
    }

    /**
     * Show mentee search page string.
     *
     * @return the string
     */
    @GetMapping("/search")
    public String showMenteeSearchPage() {
        return "searchmentee";
    }

    /**
     * Search mentee string.
     *
     * @param rollnumber    the rollnumber
     * @param model the model
     * @return the string
     */
    @GetMapping("/searchresult")
    public String searchMentee(@RequestParam(value = "rollnumber", required = false) String rollnumber, Model model) {
        if (null == rollnumber) {
            model.addAttribute("message", "roll number should not be null");
            return "searchmentee";
        }
        Mentee menteeByRollNumber = menteeService.findMenteeByRollNumber(rollnumber);

        if (menteeByRollNumber != null) {
            model.addAttribute("mentee", menteeByRollNumber);
            model.addAttribute("menteeNotes", menteeByRollNumber.getMenteeNotes());
            return "menteewithnotes";

        }
        model.addAttribute("message", "No user found against roll number: " + rollnumber);
        return "searchmentee";
    }

    /**
     * Stats string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/stats")
    public String stats(Model model) {
        model.addAttribute("numberofmentees", menteeService.numberOfMentees());
        model.addAttribute("averagenotes", menteeService.averageNotes());
        return "statistics";
    }

    /**
     * Show add note form string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/note/form/{id}")
    public String showAddNoteForm(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("note", new Note());
        model.addAttribute("menteeid", id);
        return "addnewnote";
    }

    /**
     * Add new note string.
     *
     * @param note          the note
     * @param bindingResult the binding result
     * @param id            the id
     * @return the string
     */
    @PostMapping("/note/form")
    public String addNewNote(@Valid Note note, BindingResult bindingResult, @RequestParam(value = "menteeid") Integer id) {
        if (bindingResult.hasErrors()) {
            return "addnewnote";
        } else {
            menteeService.addNewNote(id, note);
            return "redirect:/mentees/list";
        }
    }

    /**
     * Zero notes mentees string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/withzeronotes")
    public String zeroNotesMentees(Model model) {
        model.addAttribute("menteesList", menteeService.menteesWithNoNotes());
        return "zeronotesmentees";
    }

    /**
     * More than 4 notes mentees string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/morethanfournotes")
    public String moreThan4NotesMentees(Model model) {
        model.addAttribute("menteesList", menteeService.menteesWithMoreThan4Notes());
        return "morethan4notes";
    }

}
