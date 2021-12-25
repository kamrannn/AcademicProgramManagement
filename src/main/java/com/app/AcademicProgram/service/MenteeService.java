package com.app.AcademicProgram.service;

import com.app.AcademicProgram.model.Mentee;
import com.app.AcademicProgram.model.Note;
import com.app.AcademicProgram.repository.MenteeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Mentee service.
 */
@Service
public class MenteeService {
    /**
     * The Mentee repository.
     */
    MenteeRepository menteeRepository;

    /**
     * Instantiates a new Mentee service.
     *
     * @param menteeRepository the mentee repository
     */
    @Autowired
    public MenteeService(MenteeRepository menteeRepository) {
        this.menteeRepository = menteeRepository;
    }

    /**
     * Mentees list list.
     *
     * @return the list
     */
    public List<Mentee> menteesList() {
        return menteeRepository.findAll();
    }

    /**
     * Mentee notes list.
     *
     * @param menteeId the mentee id
     * @return the list
     */
    public List<Note> menteeNotes(Integer menteeId) {
        Optional<Mentee> mentee = menteeRepository.findById(menteeId);
        if (mentee.isPresent()) {
            return mentee.get().getMenteeNotes();
        } else {
            return null;
        }
    }

    /**
     * Save mentee.
     *
     * @param mentee the mentee
     */
    public void saveMentee(Mentee mentee) {
        menteeRepository.save(mentee);
    }

    /**
     * Delete mentee.
     *
     * @param menteeId the mentee id
     */
    public void deleteMentee(Integer menteeId) {
        Optional<Mentee> mentee = menteeRepository.findById(menteeId);
        if (mentee.isPresent()) {
            menteeRepository.delete(mentee.get());
        } else {
            throw new RuntimeException();
        }
    }

    /**
     * Find mentee by email mentee.
     *
     * @param email the email
     * @return the mentee
     */
    public Mentee findMenteeByEmail(String email) {
        Optional<Mentee> mentee = menteeRepository.findByEmail(email);
        if (mentee.isPresent()) {
            return mentee.get();
        } else {
            return null;
        }
    }

    /**
     * Find mentee by id mentee.
     *
     * @param menteeId the mentee id
     * @return the mentee
     */
    public Mentee findMenteeById(Integer menteeId) {
        Optional<Mentee> mentee = menteeRepository.findById(menteeId);
        if (mentee.isPresent()) {
            return mentee.get();
        } else {
            return null;
        }
    }

    public Mentee findMenteeByRollNumber(String rollNumber){
        Optional<Mentee> mentee = menteeRepository.findMenteeByRollNumber(rollNumber);
        if(mentee.isPresent()){
            return mentee.get();
        }else{
            return null;
        }
    }

    /**
     * Add new note.
     *
     * @param menteeId the mentee id
     * @param note     the note
     */
    public void addNewNote(Integer menteeId, Note note) {
        Optional<Mentee> mentee = menteeRepository.findById(menteeId);
        if (mentee.isPresent()) {
            long millis = System.currentTimeMillis();
            Date date = new Date(millis);
            List<Note> notes = mentee.get().getMenteeNotes();
            note.setDateOfCreation(date);
            notes.add(note);
            menteeRepository.save(mentee.get());
        } else {
            throw new RuntimeException();
        }
    }

    /**
     * Number of mentees integer.
     *
     * @return the integer
     */
    public Integer numberOfMentees() {
        List<Mentee> menteesList = menteeRepository.findAll();
        Integer totalMentees = menteesList.size();
        return totalMentees;
    }

    /**
     * Average notes double.
     *
     * @return the double
     */
    public double averageNotes() {
        List<Mentee> menteestList = menteeRepository.findAll();
        Integer totalMentees = menteestList.size();
        int totalNotes = 0;
        for (Mentee mentee : menteestList
        ) {
            List<Note> notes;
            notes = mentee.getMenteeNotes();
            totalNotes = totalNotes + notes.size();
        }
        if(totalMentees==0 || totalNotes==0){
            return 0;
        }
        double result = totalNotes / totalMentees;
        return result;
    }

    /**
     * Mentees with no notes list.
     *
     * @return the list
     */
    public List<Mentee> menteesWithNoNotes(){
        List<Mentee> menteesList = menteeRepository.findAll();
        List<Mentee> noNotesMentees = new ArrayList<>();
        for (Mentee mentee:menteesList
        ) {
            if(mentee.getMenteeNotes().isEmpty()){
                noNotesMentees.add(mentee);
            }
        }
        return noNotesMentees;
    }

    /**
     * Mentees with more than 4 notes list.
     *
     * @return the list
     */
    public List<Mentee> menteesWithMoreThan4Notes(){
        List<Mentee> menteesList = menteeRepository.findAll();
        List<Mentee> menteesWithMoreThan4Notes = new ArrayList<>();
        for (Mentee mentee:menteesList
        ) {
            if(mentee.getMenteeNotes().size()>4){
                menteesWithMoreThan4Notes.add(mentee);
            }
        }
        return menteesWithMoreThan4Notes;
    }

    public Mentee findByEmailAndRollNumberAndId(String email, String rollNumber, Integer id){
        Optional<Mentee> mentee = menteeRepository.findMenteeByEmailAndRollNumberAndId(email,rollNumber,id);
        return mentee.get();
    }
    
}
