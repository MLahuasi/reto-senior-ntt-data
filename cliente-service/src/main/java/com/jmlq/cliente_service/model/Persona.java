package com.jmlq.cliente_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    protected String nombre;

    @Column(nullable = false)
    protected String genero;

    @Column(nullable = false)
    protected Integer edad;

    @Column(nullable = false, unique = true)
    protected String identificacion;

    @Column(nullable = false)
    protected String direccion;

    @Column(nullable = false)
    protected String telefono;
}
