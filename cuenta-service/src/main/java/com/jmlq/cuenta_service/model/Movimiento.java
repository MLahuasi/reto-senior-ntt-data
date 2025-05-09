package com.jmlq.cuenta_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Representa un movimiento (débito/crédito) en una cuenta.
 */
@Entity
@Table(name = "movimiento")
@Getter
@Setter
@NoArgsConstructor
public class Movimiento {

    /** PK auto-generada */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Fecha y hora del movimiento */
    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    /** Tipo de movimiento (DEBITO, CREDITO, etc.) */
    @Column(name = "tipo_movimiento")
    private String tipoMovimiento;

    /** Valor del movimiento */
    @Column(name = "valor")
    private BigDecimal valor;

    /** Saldo resultante después del movimiento */
    @Column(name = "saldo")
    private BigDecimal saldo;

    /**
     * Muchos movimientos pertenecen a una cuenta.
     * FK cuenta_id en la tabla movimiento.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_id", nullable = false)
    private Cuenta cuenta;
}
