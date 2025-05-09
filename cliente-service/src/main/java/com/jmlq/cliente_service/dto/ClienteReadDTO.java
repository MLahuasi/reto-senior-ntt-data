package com.jmlq.cliente_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteReadDTO {
    private PersonaDTO persona;
    private Long personaId;
    private Boolean estado;
}
