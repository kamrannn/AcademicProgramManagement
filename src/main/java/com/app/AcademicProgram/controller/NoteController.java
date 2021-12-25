package com.app.AcademicProgram.controller;

import com.app.AcademicProgram.model.Note;
import com.app.AcademicProgram.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The type Note controller.
 */
@Controller
@RequestMapping("/notes")
public class NoteController {
    /**
     * The Note service.
     */
    NoteService noteService;

    /**
     * Instantiates a new Note controller.
     *
     * @param noteService the note service
     */
    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    /**
     * Delete note string.
     *
     * @param noteId   the note id
     * @param menteeid the menteeid
     * @return the string
     */
    @GetMapping("/delete/{id}/{menteeid}")
    public String deleteNote(@PathVariable(value = "id") Integer noteId,@PathVariable(value = "menteeid") Integer menteeid) {
        noteService.deleteNote(noteId);
        return "redirect:/mentees/notes/"+menteeid;
    }

    /**
     * Update note form string.
     *
     * @param id       the id
     * @param menteeid the menteeid
     * @param model    the model
     * @return the string
     */
    @GetMapping("/update/{id}/{menteeid}")
    public String updateNoteForm(@PathVariable(value = "id") Integer id,@PathVariable(value = "menteeid") Integer menteeid, Model model) {
        Note note = noteService.findNoteById(id);
        model.addAttribute("note", note);
        model.addAttribute("menteeid",menteeid);
        return "updatenoteform";
    }

    /**
     * Update note string.
     *
     * @param note          the note
     * @param bindingResult the binding result
     * @param menteeid      the menteeid
     * @return the string
     */
    @PostMapping("/update")
    public String updateNote(@Valid Note note, BindingResult bindingResult,@RequestParam(value = "menteeid") Integer menteeid) {
        if (bindingResult.hasErrors()) {
            return "updatenoteform";
        } else {
            noteService.saveNote(note);
            return "redirect:/mentees/notes/"+menteeid;
        }
    }
}
