package com.jmlq.cuenta_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una cuenta bancaria.
 * Almacena solo el clienteId para luego enriquecer vía llamada REST.
 */
@Entity
@Table(name = "cuenta")
@Getter
@Setter
@NoArgsConstructor
public class Cuenta {

    /** PK auto-generada */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Número de cuenta único */
    @Column(name = "numero_cuenta", unique = true, nullable = false)
    private String numeroCuenta;

    /** Tipo de cuenta (Ahorros, Corriente, etc.) */
    @Column(name = "tipo_cuenta")
    private String tipoCuenta;

    /** Saldo inicial definido al crear la cuenta */
    @Column(name = "saldo_inicial")
    private BigDecimal saldoInicial;

    /** Estado activo/inactivo */
    @Column(name = "estado")
    private Boolean estado;

    /**
     * FK a cliente-service (cliente.persona_id).
     * Sólo guardamos el ID, la info adicional llega por REST.
     */
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    /**
     * Relación uno-a-muchos con movimientos.
     * Cuando se borra cuenta, se eliminan sus movimientos.
     */
    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Movimiento> movimientos = new ArrayList<>();
}
