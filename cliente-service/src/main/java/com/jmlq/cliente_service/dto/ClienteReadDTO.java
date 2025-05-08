package com.jmlq.cliente_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteReadDTO {
    private Long id;
    private PersonaDTO persona;
    private String clienteId;
    private Boolean estado;
}
