package com.jmlq.cuenta_service.service;

import java.util.List;

import com.jmlq.cuenta_service.dto.MovimientoCreateDTO;
import com.jmlq.cuenta_service.dto.MovimientoReadDTO;
import com.jmlq.cuenta_service.dto.MovimientoUpdateDTO;

public interface MovimientoService {
    MovimientoReadDTO create(MovimientoCreateDTO dto);

    MovimientoReadDTO getById(Long id);

    List<MovimientoReadDTO> getAll();

    void delete(Long id);

    // ← nuevo
    MovimientoReadDTO update(MovimientoUpdateDTO dto);

    List<MovimientoReadDTO> getByCuenta(Long cuentaId);
}