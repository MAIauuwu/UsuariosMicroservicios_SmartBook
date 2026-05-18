package org.smartbook.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "apoderados")
@Data
@EqualsAndHashCode(callSuper = true)
public class Apoderado extends User {

    @Column(nullable = false)
    private String parentesco;

    @Column(nullable = false)
    private String telefono;

    private String direccion;

    private String documentoIdentidad;
}
