package com.jmlq.cuenta_service.service;

import java.util.List;

import com.jmlq.cuenta_service.dto.EstadoCuentaDTO;
import com.jmlq.cuenta_service.dto.ReporteEstadoCuentaDTO;

public interface ReporteService {

    List<EstadoCuentaDTO> generarEstadoCuenta(ReporteEstadoCuentaDTO request);
}