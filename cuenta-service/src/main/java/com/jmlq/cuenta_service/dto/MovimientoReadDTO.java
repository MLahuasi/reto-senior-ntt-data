package com.jmlq.cuenta_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO para lectura de Movimiento.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoReadDTO {
    private Long id;
    private LocalDateTime fecha;
    private String tipo;
    private BigDecimal valor;
    private BigDecimal saldoResultante;

    // Getters y setters omitted for brevity
}