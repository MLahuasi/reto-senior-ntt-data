package com.jmlq.cuenta_service.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClienteCreatedEvent {
    private final Long clienteId;
    private final String nombre;
    private final String genero;
    private final Integer edad;
    private final String identificacion;
    private final String direccion;
    private final String telefono;
    private final String contrasena;
    private final Boolean estado;

    @JsonCreator
    public ClienteCreatedEvent(
            @JsonProperty("clienteId") Long clienteId,
            @JsonProperty("nombre") String nombre,
            @JsonProperty("genero") String genero,
            @JsonProperty("edad") Integer edad,
            @JsonProperty("identificacion") String identificacion,
            @JsonProperty("direccion") String direccion,
            @JsonProperty("telefono") String telefono,
            @JsonProperty("contrasena") String contrasena,
            @JsonProperty("estado") Boolean estado) {
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.genero = genero;
        this.edad = edad;
        this.identificacion = identificacion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.estado = estado;
    }

    public Long getClienteId() {
        System.out.println("ClienteCreatedEvent - Cliente ID: " + clienteId);
        return clienteId;
    }

    public String getNombre() {
        return nombre;
    };

    public String getGenero() {
        return genero;
    };

    public Integer getEdad() {
        return edad;
    };

    public String getIdentificacion() {
        return identificacion;
    };

    public String getDireccion() {
        return direccion;
    };

    public String getTelefono() {
        return telefono;
    };

    public String getContrasena() {
        return contrasena;
    };

    public Boolean getEstado() {
        return estado;
    };
}
