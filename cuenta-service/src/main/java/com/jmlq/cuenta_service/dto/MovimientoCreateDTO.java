package com.jmlq.cuenta_service.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

/**
 * DTO para creación de Movimiento.
 */
public class MovimientoCreateDTO {
    @NotNull(message = "El id de la cuenta es obligatorio")
    private Long cuentaId;

    @NotBlank(message = "El tipo de movimiento es obligatorio")
    @Pattern(regexp = "DEBITO|CREDITO", message = "El tipo de movimiento debe ser 'DEBITO' o 'CREDITO'")
    private String tipo;

    @NotNull(message = "El valor del movimiento es obligatorio")
    @Positive(message = "El valor debe ser mayor a 0")
    private BigDecimal valor;

    // Getters y setters
    public Long getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Long cuentaId) {
        this.cuentaId = cuentaId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}