package com.app.AcademicProgram.service;

import com.app.AcademicProgram.model.Note;
import com.app.AcademicProgram.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Note service.
 */
@Service
public class NoteService {
    /**
     * The Note repository.
     */
    NoteRepository noteRepository;

    /**
     * Instantiates a new Note service.
     *
     * @param noteRepository the note repository
     */
    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    /**
     * Delete note.
     *
     * @param noteId the note id
     */
    public void deleteNote(Integer noteId) {
        Optional<Note> note = noteRepository.findById(noteId);
        if (note.isPresent()) {
            noteRepository.delete(note.get());
        } else {
            throw new RuntimeException();
        }
    }

    /**
     * Find note by id note.
     *
     * @param noteId the note id
     * @return the note
     */
    public Note findNoteById(Integer noteId) {
        Optional<Note> note = noteRepository.findById(noteId);
        if (note.isPresent()) {
            return note.get();
        } else {
            return null;
        }
    }

    /**
     * Save note.
     *
     * @param note the note
     */
    public void saveNote(Note note){
        noteRepository.save(note);
    }
}
