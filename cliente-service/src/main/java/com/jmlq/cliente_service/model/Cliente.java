package com.jmlq.cliente_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "persona_id", referencedColumnName = "id")
@Getter
@Setter
@NoArgsConstructor
public class Cliente extends Persona {
    // no hace falta definir un campo personaId: JPA lo manejará implícitamente
    private String contrasena;
    private Boolean estado;
}