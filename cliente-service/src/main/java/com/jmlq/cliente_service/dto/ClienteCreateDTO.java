package com.jmlq.cliente_service.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCreateDTO {

        @NotBlank
        @Size(max = 100)
        private String nombre;

        @NotBlank
        @Pattern(regexp = "M|F")
        private String genero;

        @NotNull
        @Min(0)
        private Integer edad;

        @NotBlank
        private String identificacion;

        @NotBlank
        private String direccion;

        @NotBlank
        private String telefono;

        @NotBlank
        @Size(min = 8, max = 100)
        private String contrasena;

        @NotNull
        private Boolean estado;
}