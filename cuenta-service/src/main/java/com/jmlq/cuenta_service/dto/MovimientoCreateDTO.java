package com.jmlq.cuenta_service.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoCreateDTO {
    /**
     * Fecha y hora del movimiento.
     * Si se desea que el sistema asigne la fecha automáticamente,
     * este campo puede omitirse o bien manejarse desde el servicio.
     */
    private LocalDateTime fecha;

    /** Tipo de movimiento (e.g., "DEPOSITO", "RETIRO"). */
    private String tipoMovimiento;

    /** Valor monetario del movimiento. */
    private BigDecimal valor;
}