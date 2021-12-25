package com.app.AcademicProgram.repository;

import com.app.AcademicProgram.model.Mentee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface Mentee repository.
 */
@Repository
public interface MenteeRepository extends JpaRepository<Mentee, Integer> {
    /**
     * Find by email optional.
     *
     * @param email the email
     * @return the optional
     */
    Optional<Mentee> findByEmail(String email);

    Optional<Mentee> findMenteeByRollNumber(String rollNumber);

    Optional<Mentee> findMenteeByEmailAndRollNumberAndId(String email, String rollNumber,Integer id);
}
