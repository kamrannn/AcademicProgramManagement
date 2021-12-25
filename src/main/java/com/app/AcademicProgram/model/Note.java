package com.app.AcademicProgram.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

/**
 * The type Note.
 */
@Data
@Entity
@Table(name = "notes")
public class Note {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /**
     * The Text.
     */
    @NotBlank
    String text;
    /**
     * The Date of creation.
     */
    Date dateOfCreation;
}
