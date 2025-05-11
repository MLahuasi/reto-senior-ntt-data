package com.jmlq.cliente_service.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCreateDTO {

        @NotBlank(message = "El nombre es obligatorio")
        private String nombre;

        @NotBlank(message = "El género es obligatorio")
        @Pattern(regexp = "M|F", message = "El género debe ser 'M' o 'F'")
        private String genero;

        @NotNull(message = "La edad es obligatoria")
        @Min(value = 0, message = "La edad debe ser 0 o mayor")
        private Integer edad;

        @NotBlank(message = "La identificación es obligatoria")
        private String identificacion;

        @NotBlank(message = "La dirección es obligatoria")
        private String direccion;

        @NotBlank(message = "El teléfono es obligatorio")
        private String telefono;

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 4, max = 8, message = "La contraseña debe tener entre 4 y 8 caracteres")
        private String contrasena;

        @NotNull(message = "El estado es obligatorio")
        private Boolean estado;

        @NotNull(message = "El saldoInicial es obligatoria")
        @Min(value = 0, message = "El saldoInicial debe ser 0 o mayor")
        private BigDecimal saldoInicial;

        @NotBlank(message = "El numeroCuenta es obligatorio")
        private String numeroCuenta;

        @NotNull(message = "El tipoCuenta es obligatorio")
        @Pattern(regexp = "AHORRO|CORRIENTE", message = "El tipo de cuenta debe ser 'AHORRO' o 'CORRIENTE'")
        private String tipoCuenta;
}