package com.jmlq.cliente_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDTO {
        private Long id;
        private String nombre;
        private String genero;
        private Integer edad;
        private String identificacion;
        private String direccion;
        private String telefono;
}