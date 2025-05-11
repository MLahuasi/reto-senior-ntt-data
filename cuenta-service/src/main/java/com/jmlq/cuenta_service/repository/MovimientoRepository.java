package com.jmlq.cuenta_service.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmlq.cuenta_service.model.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    List<Movimiento> findByCuentaIdAndFechaBetween(
            Long cuentaId,
            LocalDateTime desde,
            LocalDateTime hasta);

    List<Movimiento> findByCuentaIdAndFechaBefore(Long cuentaId,
            LocalDateTime fechaTope);
}