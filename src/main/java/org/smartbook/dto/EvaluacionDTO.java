package org.smartbook.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionDTO {
    private Integer id;

    @NotBlank(message = "El nombre de la evaluación no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(min = 5, max = 500, message = "La descripción debe tener entre 5 y 500 caracteres")
    private String descripcion;

    @NotNull(message = "El curso es requerido")
    private Integer cursoId;

    @NotBlank(message = "La fecha no puede estar vacía")
    private String fecha;

    @NotNull(message = "El puntaje máximo es requerido")
    @Min(value = 1, message = "El puntaje máximo debe ser mayor a 0")
    @Max(value = 100, message = "El puntaje máximo debe ser menor o igual a 100")
    private Integer puntajeMaximo;

    private String createdAt;
}

