package com.jmlq.cliente_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmlq.cliente_service.model.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

}
