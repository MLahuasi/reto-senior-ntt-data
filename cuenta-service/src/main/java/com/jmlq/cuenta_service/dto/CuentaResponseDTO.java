package com.jmlq.cuenta_service.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CuentaResponseDTO {
    private Long id;
    private String numeroCuenta;
    private String tipoCuenta;
    private BigDecimal saldoInicial;
    private Boolean estado;
    private String clienteNombre; // campo extra
}