package com.jmlq.cliente_service.event;

import java.io.Serializable;

// Evento que se envía cuando un Cliente es creado
public class ClienteCreatedEvent implements Serializable {
    private Long clienteId;

    public ClienteCreatedEvent() {
    }

    public ClienteCreatedEvent(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
}