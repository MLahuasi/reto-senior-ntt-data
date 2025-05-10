package com.jmlq.cliente_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteUpdateDTO {
    private Long personaId;
    private String contrasena;
    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "true|false")
    private Boolean estado;

    // campos de Persona “a nivel”
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "El género es obligatorio")
    @Pattern(regexp = "M|F")
    private String genero;
    @Min(value = 0, message = "La edad debe ser positiva")
    private Integer edad;
    @NotBlank(message = "La identificación es obligatoria")
    private String identificacion;
    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;
    @NotBlank(message = "El teléfono es obligatorio")
    private String telefono;
}