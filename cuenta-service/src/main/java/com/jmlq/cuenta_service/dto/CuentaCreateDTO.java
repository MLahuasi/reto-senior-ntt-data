package com.jmlq.cuenta_service.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaCreateDTO {

        @NotBlank
        @Size(max = 50)
        private String numeroCuenta;

        @NotBlank
        private String tipoCuenta;

        @NotNull
        @DecimalMin("0.00")
        private BigDecimal saldoInicial;

        @NotNull
        private Boolean estado;
}