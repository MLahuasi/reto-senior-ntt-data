package com.jmlq.cliente_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponseDTO {
        private Long id;
        private PersonaDTO persona;
        private String clienteId;
        private Boolean estado;
}