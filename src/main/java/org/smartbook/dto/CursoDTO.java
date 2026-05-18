package org.smartbook.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoDTO {
    private Integer id;

    @NotBlank(message = "El nombre del curso no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El nivel no puede estar vacío")
    private String nivel;

    @NotNull(message = "El año es requerido")
    @Min(value = 2000, message = "El año debe ser mayor a 2000")
    @Max(value = 2100, message = "El año debe ser menor a 2100")
    private Integer anio;
}

