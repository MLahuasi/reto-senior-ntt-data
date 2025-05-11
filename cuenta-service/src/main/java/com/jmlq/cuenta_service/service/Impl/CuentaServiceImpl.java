package com.jmlq.cuenta_service.service.Impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.jmlq.cuenta_service.dto.ClienteInfoDTO;
import com.jmlq.cuenta_service.dto.CuentaCreateDTO;
import com.jmlq.cuenta_service.dto.CuentaReadDTO;
import com.jmlq.cuenta_service.dto.CuentaUpdateDTO;
import com.jmlq.cuenta_service.model.Cuenta;
import com.jmlq.cuenta_service.repository.CuentaRepository;
import com.jmlq.cuenta_service.repository.MovimientoRepository;
import com.jmlq.cuenta_service.service.CuentaService;
import com.jmlq.cuenta_service.exception.EntityNotFoundException;
import com.jmlq.cuenta_service.exception.ResourceNotFoundException;

@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;
    private RestTemplate restTemplate;
    private String clienteServiceUrl;

    public CuentaServiceImpl(CuentaRepository cuentaRepository,
            MovimientoRepository movRepo,
            RestTemplate restTemplate,
            @Value("${cliente.service.url}") String clienteServiceUrl) {
        this.cuentaRepository = cuentaRepository;
        this.clienteServiceUrl = clienteServiceUrl;
        this.restTemplate = restTemplate;
    }

    public String getClienteNombre(Long clienteId) {
        String clienteUrl = clienteServiceUrl + "/api/v1/clientes/" + clienteId;
        ClienteInfoDTO cliente = restTemplate.getForObject(clienteUrl, ClienteInfoDTO.class);
        if (cliente == null || cliente.getPersona() == null) {
            throw new EntityNotFoundException("Cliente no encontrado con ID: " + clienteId);
        }
        return cliente.getPersona().getNombre();
    }

    @Override
    public CuentaReadDTO create(CuentaCreateDTO dto) {

        String nombreCliente = getClienteNombre(dto.getClienteId());

        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(dto.getNumeroCuenta());
        cuenta.setTipoCuenta(dto.getTipo());
        cuenta.setSaldoInicial(dto.getSaldoInicial());
        cuenta.setEstado(true);
        cuenta.setClienteId(dto.getClienteId());
        Cuenta guardada = cuentaRepository.save(cuenta);
        return mapToReadDTO(guardada, nombreCliente);
    }

    @Override
    public CuentaReadDTO getById(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con id " + id));

        String nombreCliente = getClienteNombre(cuenta.getClienteId());

        return mapToReadDTO(cuenta, nombreCliente);
    }

    @Override
    public List<CuentaReadDTO> getAll() {
        return cuentaRepository.findAll().stream()
                .map(cuenta -> {
                    String nombreCliente = getClienteNombre(cuenta.getClienteId());
                    return mapToReadDTO(cuenta, nombreCliente);
                })
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
    public Long createDefaultAccountForClient(Long clienteId, String numeroCuenta, BigDecimal saldoInicial,
            String tipoCuenta) {
        // Preparamos DTO con valores iniciales
        CuentaCreateDTO dto = new CuentaCreateDTO();
        dto.setClienteId(clienteId);
        dto.setTipo(tipoCuenta);
        dto.setSaldoInicial(saldoInicial);
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

    private CuentaReadDTO mapToReadDTO(Cuenta cuenta, String nombreCliente) {
        CuentaReadDTO dto = new CuentaReadDTO();
        dto.setId(cuenta.getId());
        dto.setNumeroCuenta(cuenta.getNumeroCuenta());
        dto.setTipo(cuenta.getTipoCuenta());
        dto.setSaldo(cuenta.getSaldoInicial());
        dto.setEstado(cuenta.getEstado());
        dto.setClienteNombre(nombreCliente);
        return dto;
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