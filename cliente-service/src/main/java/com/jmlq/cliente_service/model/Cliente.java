package com.jmlq.cliente_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "id") // la PK “id” hereda de persona.id
@Getter
@Setter
@NoArgsConstructor
public class Cliente extends Persona {
    @Column(name = "cliente_id", unique = true, nullable = false)
    private String clienteId;

    private String contrasena;
    private Boolean estado;
}