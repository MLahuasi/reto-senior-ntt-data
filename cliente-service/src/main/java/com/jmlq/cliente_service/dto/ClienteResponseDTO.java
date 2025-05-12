package com.jmlq.cliente_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponseDTO {
        private PersonaDTO persona;
        private Long personaId;
        private Boolean estado;
}