package com.jmlq.cliente_service.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad base para datos de persona.
 * Se mapea a la tabla “persona” con herencia JOINED.
 */
@Entity
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
public class Persona {

    /** PK generado automáticamente */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    /** Nombre completo de la persona */
    protected String nombre;

    /** Género (M/F/u otro) */
    protected String genero;

    /** Edad en años */
    protected Integer edad;

    /** Documento de identificación */
    protected String identificacion;

    /** Dirección postal */
    protected String direccion;

    /** Teléfono de contacto */
    protected String telefono;
}
