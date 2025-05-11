package com.jmlq.cuenta_service.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Representa el reporte de estado de cuenta para un día.
 */
@Getter
@Setter
public class EstadoCuentaDTO {
    private Long cuentaId;
    private String clienteNombre;
    private BigDecimal saldoInicial;
    private BigDecimal saldoFinal;
    private List<MovimientoReadDTO> movimientos;

    public EstadoCuentaDTO(Long cuentaId,
            String clienteNombre,
            BigDecimal saldoInicial,
            BigDecimal saldoFinal,
            List<MovimientoReadDTO> movimientos) {
        this.cuentaId = cuentaId;
        this.clienteNombre = clienteNombre;
        this.saldoInicial = saldoInicial;
        this.saldoFinal = saldoFinal;
        this.movimientos = movimientos;
    }
}