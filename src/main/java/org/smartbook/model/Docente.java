package org.smartbook.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "docentes")
@Data
@EqualsAndHashCode(callSuper = true)
public class Docente extends User {

    @Column(nullable = false, unique = true)
    private String numeroColegiatura;

    private String especialidad;

    private String gradoAcademico;
}
