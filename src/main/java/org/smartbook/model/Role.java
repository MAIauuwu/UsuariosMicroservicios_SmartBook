package org.smartbook.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "roles")
@Data

public class Role {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Interger id;

    @Column(nullable = false)
    private String nombre;

}
