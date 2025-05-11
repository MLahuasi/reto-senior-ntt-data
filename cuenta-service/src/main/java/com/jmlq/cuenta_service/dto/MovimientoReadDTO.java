package com.jmlq.cuenta_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovimientoReadDTO {
    private Long id;
    private BigDecimal valor;
    private String tipo;
    private Long cuentaId;
    private LocalDateTime fecha;

    public MovimientoReadDTO(Long id,
            BigDecimal valor,
            String tipo,
            Long cuentaId,
            LocalDateTime fecha) {
        this.id = id;
        this.valor = valor;
        this.tipo = tipo;
        this.cuentaId = cuentaId;
        this.fecha = fecha;
    }
}