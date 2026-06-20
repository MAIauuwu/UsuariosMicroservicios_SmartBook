package org.smartbook.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "estudiantes")
@Data
@EqualsAndHashCode(callSuper = true)
public class Estudiante extends User {

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    private String matricula;
}


