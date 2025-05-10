package com.jmlq.cliente_service.service;

import java.util.List;

import com.jmlq.cliente_service.dto.ClienteCreateDTO;
import com.jmlq.cliente_service.dto.ClienteReadDTO;
import com.jmlq.cliente_service.dto.ClienteResponseDTO;
import com.jmlq.cliente_service.dto.ClienteUpdateDTO;

public interface ClienteService {
    ClienteResponseDTO create(ClienteCreateDTO dto);

    ClienteResponseDTO findById(Long id);

    List<ClienteReadDTO> findAll();

    ClienteResponseDTO update(ClienteUpdateDTO dto);

    void delete(Long id);
}