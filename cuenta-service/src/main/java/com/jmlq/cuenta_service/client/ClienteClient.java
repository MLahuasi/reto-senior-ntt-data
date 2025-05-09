package com.jmlq.cuenta_service.client;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Componente que llama al servicio de clientes para obtener datos del cliente.
 */
@Component
public class ClienteClient {

    private final RestTemplate restTemplate;

    public ClienteClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getClienteNombre(Long clienteId) {
        // Al tener rootUri configurado, basta con la ruta relativa
        @SuppressWarnings("unchecked")
        Map<String, Object> respuesta = restTemplate.getForObject("/api/v1/clientes/{id}", Map.class, clienteId);
        return respuesta != null
                ? (String) respuesta.get("nombre")
                : null;
    }
}