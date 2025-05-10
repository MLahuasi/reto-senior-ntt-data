package com.jmlq.cliente_service.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.jmlq.cliente_service.dto.CuentaDTO;

@Component
public class CuentaCliente {

    private final RestTemplate rt;

    public CuentaCliente(RestTemplate rt) {
        this.rt = rt;
    }

    /**
     * Obtiene todas las cuentas de un cliente, vía GET /cuentas?clienteId={id}
     */
    public List<CuentaDTO> getCuentasByCliente(Long clienteId) {
        ResponseEntity<List<CuentaDTO>> resp = rt.exchange(
                "/api/v1/clientes?clienteId={id}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                clienteId);
        return resp.getBody();
    }
}