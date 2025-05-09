package com.jmlq.cuenta_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jmlq.cuenta_service.client.ClienteClient;
import com.jmlq.cuenta_service.dto.CuentaCreateRequestDTO;
import com.jmlq.cuenta_service.dto.CuentaResponseDTO;
import com.jmlq.cuenta_service.mapper.CuentaMapper;
import com.jmlq.cuenta_service.model.Cuenta;
import com.jmlq.cuenta_service.repository.CuentaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CuentaService {
    private final CuentaRepository repo;
    private final CuentaMapper mapper;
    private final ClienteClient clienteClient;

    public CuentaService(CuentaRepository repo,
            CuentaMapper mapper,
            ClienteClient clienteClient) {
        this.repo = repo;
        this.mapper = mapper;
        this.clienteClient = clienteClient;
    }

    public CuentaResponseDTO crearCuenta(CuentaCreateRequestDTO req) {
        // 1) DTO → Entidad
        Cuenta entidad = mapper.toEntity(req);
        // 2) Guardar en BD
        Cuenta saved = repo.save(entidad);
        // 3) Entidad → DTO respuesta
        CuentaResponseDTO resp = mapper.toResponse(saved);
        // 4) Enriquecer con nombre de cliente
        resp.setClienteNombre(
                clienteClient.getClienteNombre(saved.getClienteId()));
        return resp;
    }

    public CuentaResponseDTO obtenerCuenta(Long id) {
        Cuenta c = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cuenta no encontrada"));
        CuentaResponseDTO dto = mapper.toResponse(c);
        dto.setClienteNombre(clienteClient.getClienteNombre(c.getClienteId()));
        return dto;
    }

    public List<CuentaResponseDTO> listarCuentas() {
        return repo.findAll().stream()
                .map(c -> {
                    CuentaResponseDTO dto = mapper.toResponse(c);
                    dto.setClienteNombre(clienteClient.getClienteNombre(c.getClienteId()));
                    return dto;
                })
                .collect(Collectors.toList());
    }
}