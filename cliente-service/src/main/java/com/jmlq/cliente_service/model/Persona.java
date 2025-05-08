package com.jmlq.cliente_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity // <— ahora es entidad
@Table(name = "persona") // tabla “persona”
@Inheritance(strategy = InheritanceType.JOINED) // estrategia JOINED
@Getter
@Setter
@NoArgsConstructor
public abstract class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String nombre;
    protected String genero;
    protected Integer edad;
    protected String identificacion;
    protected String direccion;
    protected String telefono;
}