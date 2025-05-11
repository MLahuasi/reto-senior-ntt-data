package com.jmlq.cuenta_service.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaReadDTO {
    private Long id;
    private String numeroCuenta;
    private String tipo;
    private BigDecimal saldo;
    private Boolean estado;
    private String clienteNombre;
}