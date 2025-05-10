package com.jmlq.cuenta_service.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaUpdateDTO {

    @NotNull(message = "El id de la cuenta es obligatorio")
    private Long id;

    @NotBlank(message = "El número de cuenta es obligatorio")
    private String numeroCuenta;

    @NotBlank(message = "El tipo de cuenta es obligatorio")
    private String tipo;

    @NotNull(message = "El saldo inicial es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El saldo inicial debe ser positivo o cero")
    private BigDecimal saldoInicial;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;
}