package com.app.AcademicProgram.repository;

import com.app.AcademicProgram.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Find user by username optional.
     *
     * @param username the username
     * @return the optional
     */
    Optional<User> findUserByUsername(String username);
}
