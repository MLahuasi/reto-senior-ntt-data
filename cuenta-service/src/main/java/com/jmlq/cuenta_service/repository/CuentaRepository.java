package com.jmlq.cuenta_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jmlq.cuenta_service.model.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    List<Cuenta> findByClienteId(Long clienteId);
}