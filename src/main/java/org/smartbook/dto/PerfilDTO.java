package org.smartbook.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfilDTO {
    private Integer id;

    @NotNull(message = "El ID del usuario es requerido")
    private Integer userId;

    @Size(max = 500, message = "La biografía debe tener máximo 500 caracteres")
    private String biografia;

    private String fotoUrl;

    @Size(max = 20, message = "El teléfono debe tener máximo 20 caracteres")
    private String telefono;

    @Size(max = 200, message = "La dirección debe tener máximo 200 caracteres")
    private String direccion;

    @Size(max = 100, message = "La ciudad debe tener máximo 100 caracteres")
    private String ciudad;

    @Size(max = 100, message = "El país debe tener máximo 100 caracteres")
    private String pais;

    private String createdAt;
    private String updatedAt;
}
