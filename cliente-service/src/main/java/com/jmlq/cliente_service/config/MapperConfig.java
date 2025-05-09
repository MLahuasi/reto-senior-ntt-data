package com.jmlq.cliente_service.config;

import com.jmlq.cliente_service.mapper.ClienteMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración para exponer ClienteMapper como bean,
 * usando la implementación de MapStruct.
 */
@Configuration
public class MapperConfig {

    @Bean
    public ClienteMapper clienteMapper() {
        // Mappers.getMapper() busca la impl. generada por MapStruct
        return Mappers.getMapper(ClienteMapper.class);
    }
}