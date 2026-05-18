package org.smartbook.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotaDTO {
    private Integer id;

    @NotNull(message = "El estudiante es requerido")
    private Integer estudianteId;

    @NotNull(message = "La evaluación es requerida")
    private Integer evaluacionId;

    @NotNull(message = "La nota es requerida")
    @DecimalMin(value = "0.0", message = "La nota no puede ser menor a 0")
    @DecimalMax(value = "10.0", message = "La nota no puede ser mayor a 10")
    private BigDecimal nota;
}

