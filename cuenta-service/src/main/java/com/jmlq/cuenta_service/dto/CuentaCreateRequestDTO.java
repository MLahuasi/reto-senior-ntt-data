package com.jmlq.cuenta_service.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CuentaCreateRequestDTO {
    @NotBlank
    private String numeroCuenta;
    private String tipoCuenta;
    @NotNull
    private BigDecimal saldoInicial;
    @NotNull
    private Boolean estado;
    @NotNull
    private Long clienteId;
}
