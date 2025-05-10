package com.jmlq.cuenta_service.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoUpdateDTO {

    @NotNull(message = "El id del movimiento es obligatorio")
    private Long id;

    @NotNull(message = "El id de la cuenta es obligatorio")
    private Long cuentaId;

    @NotBlank(message = "El tipo de movimiento es obligatorio")
    @Pattern(regexp = "DEBITO|CREDITO", message = "El tipo debe ser DEBITO o CREDITO")
    private String tipoMovimiento;

    @NotNull(message = "El valor del movimiento es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El valor debe ser mayor que 0")
    private BigDecimal valor;

    // opcionalmente, si almacenas saldoPostMovimiento:
    @DecimalMin(value = "0.0", inclusive = true, message = "El saldo debe ser positivo o cero")
    private BigDecimal saldo;
}