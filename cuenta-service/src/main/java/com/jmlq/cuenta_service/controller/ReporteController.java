package com.jmlq.cuenta_service.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jmlq.cuenta_service.dto.EstadoCuentaDTO;
import com.jmlq.cuenta_service.dto.ReporteEstadoCuentaDTO;
import com.jmlq.cuenta_service.service.ReporteService;

import jakarta.validation.constraints.NotNull;

//  /api/v1/reportes/estado-cuenta?clienteId=42&fechaIni=2022-02-01&fechaFin=2022-02-10
@RestController
@RequestMapping("/reportes")
@Validated
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping("/estado-cuenta")
    public List<EstadoCuentaDTO> estadoCuenta(
            @RequestParam("clienteId") @NotNull Long clienteId,
            @RequestParam("fechaIni") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @NotNull(message = "fechaIni es obligatoria") LocalDate fechaIni,

            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @NotNull(message = "fechaFin es obligatoria") LocalDate fechaFin) {
        // crear ReporteEstadoCuentaDTO
        ReporteEstadoCuentaDTO reporteEstadoCuentaDTO = new ReporteEstadoCuentaDTO();
        reporteEstadoCuentaDTO.setClienteId(clienteId);
        reporteEstadoCuentaDTO.setFechaDesde(fechaIni);
        reporteEstadoCuentaDTO.setFechaHasta(fechaFin);
        return reporteService.generarEstadoCuenta(reporteEstadoCuentaDTO);
    }
}