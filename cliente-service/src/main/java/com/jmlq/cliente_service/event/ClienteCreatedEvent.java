package com.jmlq.cliente_service.event;

import java.io.Serializable;
import java.math.BigDecimal;

// Evento que se envía cuando un Cliente es creado
public class ClienteCreatedEvent implements Serializable {
    private Long clienteId;
    private String numeroCuenta;
    private BigDecimal saldoInicial;
    private String tipoCuenta;

    public ClienteCreatedEvent() {
    }

    public ClienteCreatedEvent(Long clienteId,
            String numeroCuenta,
            BigDecimal saldoInicial,
            String tipoCuenta) {
        this.clienteId = clienteId;
        this.numeroCuenta = numeroCuenta;
        this.saldoInicial = saldoInicial;
        this.tipoCuenta = tipoCuenta;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
}