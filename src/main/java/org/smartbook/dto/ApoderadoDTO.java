package org.smartbook.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApoderadoDTO {
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

    @NotBlank(message = "El parentesco no puede estar vacío")
    @Size(max = 50, message = "El parentesco debe tener máximo 50 caracteres")
    private String parentesco;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Size(min = 7, max = 20, message = "El teléfono debe tener entre 7 y 20 caracteres")
    private String telefono;

    @Size(max = 200, message = "La dirección debe tener máximo 200 caracteres")
    private String direccion;

    @NotBlank(message = "El documento de identidad no puede estar vacío")
    @Size(min = 8, max = 20, message = "El documento de identidad debe tener entre 8 y 20 caracteres")
    private String documentoIdentidad;

    private String createdAt;
}
