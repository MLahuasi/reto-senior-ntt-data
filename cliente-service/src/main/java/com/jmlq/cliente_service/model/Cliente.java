package com.jmlq.cliente_service.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Hereda de Persona.
 * Usa la misma PK (persona_id) como FK a persona.id.
 */
@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "persona_id", referencedColumnName = "id")
@Getter
@Setter
@NoArgsConstructor
public class Cliente extends Persona {

    @Column(nullable = false)
    private String contrasena;

    @Column(nullable = false)
    private Boolean estado;
}
