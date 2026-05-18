package org.smartbook.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocenteDTO {
    private Integer id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    private String apellido;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe ser válido")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;

    @NotNull(message = "El rol es requerido")
    private Integer rolId;

    @NotBlank(message = "El número de colegiatura no puede estar vacío")
    @Size(min = 4, max = 20, message = "El número de colegiatura debe tener entre 4 y 20 caracteres")
    private String numeroColegiatura;

    @Size(max = 100, message = "La especialidad debe tener máximo 100 caracteres")
    private String especialidad;

    @Size(max = 50, message = "El grado académico debe tener máximo 50 caracteres")
    private String gradoAcademico;

    private String createdAt;
}
