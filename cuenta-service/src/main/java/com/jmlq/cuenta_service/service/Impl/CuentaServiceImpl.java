package com.jmlq.cuenta_service.service.Impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jmlq.cuenta_service.dto.CuentaCreateDTO;
import com.jmlq.cuenta_service.dto.CuentaReadDTO;
import com.jmlq.cuenta_service.dto.CuentaUpdateDTO;
import com.jmlq.cuenta_service.model.Cuenta;
import com.jmlq.cuenta_service.repository.CuentaRepository;
import com.jmlq.cuenta_service.service.CuentaService;
import com.jmlq.cuenta_service.exception.ResourceNotFoundException;

@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public CuentaReadDTO create(CuentaCreateDTO dto) {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(dto.getNumeroCuenta());
        cuenta.setTipoCuenta(dto.getTipo());
        cuenta.setSaldoInicial(dto.getSaldoInicial());
        cuenta.setEstado(dto.getEstado());
        cuenta.setClienteId(dto.getClienteId());
        Cuenta guardada = cuentaRepository.save(cuenta);
        return mapToReadDTO(guardada);
    }

    @Override
    public CuentaReadDTO getById(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con id " + id));
        return mapToReadDTO(cuenta);
    }

    @Override
    public List<CuentaReadDTO> getAll() {
        return cuentaRepository.findAll().stream()
                .map(this::mapToReadDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (!cuentaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cuenta no encontrada con id " + id);
        }
        cuentaRepository.deleteById(id);
    }

    @Override
    public CuentaReadDTO update(CuentaUpdateDTO dto) {
        Cuenta cuenta = cuentaRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con id " + dto.getId()));
        // asignamos los nuevos valores
        cuenta.setNumeroCuenta(dto.getNumeroCuenta());
        cuenta.setTipoCuenta(dto.getTipo());
        cuenta.setSaldoInicial(dto.getSaldoInicial());
        cuenta.setEstado(dto.getEstado());
        // lo guardamos
        Cuenta actualizada = cuentaRepository.save(cuenta);
        return mapToReadDTO(actualizada);
    }

    @Override
    @Transactional
    public Long createDefaultAccountForClient(Long clienteId) {
        // Preparamos DTO con valores iniciales
        CuentaCreateDTO dto = new CuentaCreateDTO();
        dto.setClienteId(clienteId);
        dto.setTipo("Corriente");
        dto.setSaldoInicial(BigDecimal.ZERO);

        // <<< Generación de un número de cuenta único >>>
        // Por ejemplo, un UUID sin guiones o cualquier otro formato que uses:
        String numeroCuenta = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        dto.setNumeroCuenta(numeroCuenta);

        // Imprime todo el objeto dto como un String para que se vean todos los valores
        System.out.println("createDefaultAccountForClient CuentaCreateDTO :  clienteId:" + dto.getClienteId() +
                " tipo:" + dto.getTipo() +
                " saldoInicial:" + dto.getSaldoInicial() +
                " numeroCuenta:" + dto.getNumeroCuenta());

        // Persistimos la cuenta
        var cuenta = this.create(dto);

        // Imprimimos el ID de la cuenta creada
        System.out.println("createDefaultAccountForClient ID de la cuenta creada: " + cuenta.getId());
        // Retornamos el ID de la cuenta creada
        return cuenta.getId();
    }

    private CuentaReadDTO mapToReadDTO(Cuenta cuenta) {
        CuentaReadDTO dto = new CuentaReadDTO();
        dto.setId(cuenta.getId());
        dto.setNumeroCuenta(cuenta.getNumeroCuenta());
        dto.setTipo(cuenta.getTipoCuenta());
        dto.setSaldo(cuenta.getSaldoInicial());
        dto.setEstado(cuenta.getEstado());
        return dto;
    }
}