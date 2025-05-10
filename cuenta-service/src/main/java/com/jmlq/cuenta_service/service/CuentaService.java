package com.jmlq.cuenta_service.service;

import java.util.List;

import com.jmlq.cuenta_service.dto.CuentaCreateDTO;
import com.jmlq.cuenta_service.dto.CuentaReadDTO;
import com.jmlq.cuenta_service.dto.CuentaUpdateDTO;

public interface CuentaService {
    CuentaReadDTO create(CuentaCreateDTO dto);

    CuentaReadDTO getById(Long id);

    List<CuentaReadDTO> getAll();

    void delete(Long id);

    CuentaReadDTO update(CuentaUpdateDTO dto);

    Long createDefaultAccountForClient(Long clienteId);
}