package com.jmlq.cuenta_service.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaResponseDTO {
        private Long id;
        private String numeroCuenta;
        private String tipoCuenta;
        private BigDecimal saldoInicial;
        private Boolean estado;
}