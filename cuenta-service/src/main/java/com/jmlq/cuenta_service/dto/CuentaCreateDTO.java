package com.jmlq.cuenta_service.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * DTO para creación de Cuenta.
 */
public class CuentaCreateDTO {
    @NotBlank(message = "El número de cuenta es obligatorio")
    private String numeroCuenta;

    @NotBlank(message = "El tipo de cuenta es obligatorio")
    private String tipo;

    @NotNull(message = "El saldo inicial no puede ser nulo")
    @PositiveOrZero(message = "El saldo inicial debe ser 0 o mayor")
    private BigDecimal saldoInicial;

    @NotBlank(message = "El estado de la cuenta es obligatorio")
    private Boolean estado;

    // Getters y setters
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}