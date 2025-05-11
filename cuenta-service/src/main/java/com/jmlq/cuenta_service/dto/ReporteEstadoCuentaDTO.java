package com.jmlq.cuenta_service.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReporteEstadoCuentaDTO {
    @NotNull(message = "clienteId es obligatorio")
    private Long clienteId;

    @NotNull(message = "fechaDesde es obligatoria")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaDesde;

    @NotNull(message = "fechaHasta es obligatoria")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaHasta;

    // Valida que desde ≤ hasta
    @AssertTrue(message = "fechaDesde debe ser anterior o igual a fechaHasta")
    public boolean isFechasValidas() {
        return fechaDesde != null
                && fechaHasta != null
                && !fechaDesde.isAfter(fechaHasta);
    }
}
