package com.jmlq.cliente_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CuentaClientConfig {

    @Value("${cuenta-service.url}")
    private String cuentaServiceUrl;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // rootUri permite usar rutas relativas luego
        return builder
                .rootUri(cuentaServiceUrl)
                .build();
    }
}