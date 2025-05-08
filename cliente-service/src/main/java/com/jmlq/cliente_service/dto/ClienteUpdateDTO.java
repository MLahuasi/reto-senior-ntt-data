package com.jmlq.cliente_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteUpdateDTO {
    private Long id;
    private String clienteId;
    private String contrasena;
    private Boolean estado;

    // campos de Persona “a nivel”
    private String nombre;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}