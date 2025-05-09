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

    /** Contraseña en texto cifrado */
    private String contrasena;

    /** Estado activo/inactivo */
    private Boolean estado;

}
