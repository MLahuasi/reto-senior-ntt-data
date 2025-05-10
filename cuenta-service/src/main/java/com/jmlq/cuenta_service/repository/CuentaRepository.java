package com.jmlq.cuenta_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jmlq.cuenta_service.model.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);
}