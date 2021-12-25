package com.app.AcademicProgram.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Mentee.
 */
@Data
@Entity
@Table(name = "mentees")
public class Mentee {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NotBlank
    @Column(unique = true)
    String rollNumber;
    /**
     * The Email.
     */
    @NotNull
    @Size(min=2, max=30)
    @Column(unique = true)
    String email;
    /**
     * The First name.
     */
    @NotNull
    @Size(min=2, max=30)
    String firstName;
    /**
     * The Sur name.
     */
    @NotNull
    @Size(min=2, max=30)
    String surName;

    @OneToMany(targetEntity = Note.class,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "mentee_id", referencedColumnName = "id")
    private List<Note> menteeNotes = new ArrayList<>();

    @Override
    public String toString() {
        return "Mentee{" +
                "id=" + id +
                ", rollNumber='" + rollNumber + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", menteeNotes=" + menteeNotes +
                '}';
    }
}
