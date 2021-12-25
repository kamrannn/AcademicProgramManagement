package com.app.AcademicProgram.repository;

import com.app.AcademicProgram.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Note repository.
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
}
