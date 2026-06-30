package org.smartbook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteApoderadoDTO {

    private Integer id;
    private Integer estudianteId;
    private String estudianteNombre;
    private String estudianteApellido;
    private Integer apoderadoId;
    private String apoderadoNombre;
    private String apoderadoApellido;
    private String apoderadoEmail;
    private String parentesco;
}
