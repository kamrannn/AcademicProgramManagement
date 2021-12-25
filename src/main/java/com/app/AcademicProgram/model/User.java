package com.app.AcademicProgram.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * The type User.
 */
@Data
@Entity
@Table(name = "users")
public class User {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /**
     * The Username.
     */
    @NotBlank
    @Size(min=2, max=30)
    @Column(unique = true)
    String username;
    /**
     * The Password.
     */
    @NotBlank
    @Size(min=2, max=30)
    String password;
    /**
     * The Role.
     */
    @NotBlank
    String role;
    /**
     * The First name.
     */
    @NotBlank
    String firstName;
    /**
     * The Last name.
     */
    String lastName;
}
