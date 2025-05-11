package com.jmlq.cuenta_service.service.Impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmlq.cuenta_service.dto.MovimientoCreateDTO;
import com.jmlq.cuenta_service.dto.MovimientoReadDTO;
import com.jmlq.cuenta_service.dto.MovimientoUpdateDTO;
import com.jmlq.cuenta_service.exception.InsufficientBalanceException;
import com.jmlq.cuenta_service.exception.ResourceNotFoundException;
import com.jmlq.cuenta_service.model.Cuenta;
import com.jmlq.cuenta_service.model.Movimiento;
import com.jmlq.cuenta_service.repository.CuentaRepository;
import com.jmlq.cuenta_service.repository.MovimientoRepository;
import com.jmlq.cuenta_service.service.MovimientoService;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    // F2: Registro de movimientos
    @Override
    public MovimientoReadDTO create(MovimientoCreateDTO dto) {
        Cuenta cuenta = cuentaRepository.findById(dto.getCuentaId())
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con id " + dto.getCuentaId()));

        BigDecimal nuevoSaldo = cuenta.getSaldoInicial();
        if ("DEBITO".equalsIgnoreCase(dto.getTipo())) {
            if (cuenta.getSaldoInicial().compareTo(dto.getValor()) < 0) {
                throw new InsufficientBalanceException("Saldo no disponible");
            }
            nuevoSaldo = nuevoSaldo.subtract(dto.getValor());
        } else {
            nuevoSaldo = nuevoSaldo.add(dto.getValor());
        }

        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);

        Movimiento mov = new Movimiento();
        mov.setCuenta(cuenta);
        mov.setFecha(LocalDateTime.now());
        mov.setTipoMovimiento(dto.getTipo());
        mov.setValor(dto.getValor());
        mov.setSaldo(nuevoSaldo);
        Movimiento guardado = movimientoRepository.save(mov);

        return mapToReadDTO(guardado);
    }

    @Override
    public MovimientoReadDTO getById(Long id) {
        Movimiento mov = movimientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado con id " + id));
        return mapToReadDTO(mov);
    }

    @Override
    public List<MovimientoReadDTO> getAll() {
        return movimientoRepository.findAll()
                .stream()
                .map(this::mapToReadDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MovimientoReadDTO update(MovimientoUpdateDTO dto) {
        Movimiento mov = movimientoRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado con id " + dto.getId()));

        if (!mov.getCuenta().getId().equals(dto.getCuentaId())) {
            var cuenta = cuentaRepository.findById(dto.getCuentaId())
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Cuenta no encontrada con id " + dto.getCuentaId()));
            mov.setCuenta(cuenta);
        }
        mov.setTipoMovimiento(dto.getTipoMovimiento());
        mov.setValor(dto.getValor());
        mov.setSaldo(dto.getSaldo());
        Movimiento actualizado = movimientoRepository.save(mov);
        return mapToReadDTO(actualizado);
    }

    @Override
    public void delete(Long id) {
        Movimiento mov = movimientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado con id " + id));
        movimientoRepository.delete(mov);
    }

    private MovimientoReadDTO mapToReadDTO(Movimiento mov) {
        MovimientoReadDTO dto = new MovimientoReadDTO();
        dto.setId(mov.getId());
        dto.setFecha(mov.getFecha());
        dto.setTipo(mov.getTipoMovimiento());
        dto.setValor(mov.getValor());
        return dto;
    }
}
