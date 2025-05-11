package com.jmlq.cuenta_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReporteEstadoCuentaRequestDTO {
    @NotNull(message = "clienteId es obligatorio")
    private Long clienteId;

}