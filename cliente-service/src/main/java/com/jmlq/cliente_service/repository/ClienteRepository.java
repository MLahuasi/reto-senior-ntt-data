package com.jmlq.cliente_service.repository;

// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jmlq.cliente_service.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}