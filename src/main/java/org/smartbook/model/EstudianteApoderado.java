package org.smartbook.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "estudiante_apoderado",
    uniqueConstraints = @UniqueConstraint(columnNames = {"estudiante_id", "apoderado_id"}),
    indexes = {
        @Index(name = "idx_ea_estudiante", columnList = "estudiante_id"),
        @Index(name = "idx_ea_apoderado", columnList = "apoderado_id")
    }
)
@Data
public class EstudianteApoderado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "estudiante_id", nullable = false)
    private Integer estudianteId;

    @Column(name = "apoderado_id", nullable = false)
    private Integer apoderadoId;

    @Column(nullable = false)
    private String parentesco;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
