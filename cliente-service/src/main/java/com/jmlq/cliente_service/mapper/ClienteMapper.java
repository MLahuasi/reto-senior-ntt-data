package com.jmlq.cliente_service.mapper;

import com.jmlq.cliente_service.dto.*;
import com.jmlq.cliente_service.model.Cliente;
import com.jmlq.cliente_service.model.Persona;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    // --- CREATE
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "clienteId", target = "clienteId")
    @Mapping(source = "contrasena", target = "contrasena")
    @Mapping(source = "estado", target = "estado")
    // los campos de Persona vienen incluidos en ClienteCreateDTO
    Cliente toEntity(ClienteCreateDTO dto);

    // --- READ (únicamente lecturas simples)
    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "id", target = "id")
    @Mapping(source = "clienteId", target = "clienteId")
    @Mapping(source = "estado", target = "estado")
    ClienteReadDTO toReadDto(Cliente entity);

    // --- UPDATE: sólo mapeamos lo que venga en el DTO
    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "id", target = "id")
    @Mapping(source = "clienteId", target = "clienteId")
    @Mapping(source = "contrasena", target = "contrasena")
    @Mapping(source = "estado", target = "estado")

    // campos “heredados” de Persona
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "genero", target = "genero")
    @Mapping(source = "edad", target = "edad")
    @Mapping(source = "identificacion", target = "identificacion")
    @Mapping(source = "direccion", target = "direccion")
    @Mapping(source = "telefono", target = "telefono")
    Cliente toEntity(ClienteUpdateDTO dto);

    // --- DELETE (sólo necesita el id)
    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "id", target = "id")
    Cliente toEntity(ClienteDeleteDTO dto);

    // --- RESPONSE COMPLETO (incluye persona embebida)
    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "clienteId", target = "clienteId")
    @Mapping(source = "estado", target = "estado")
    ClienteResponseDTO toResponse(Cliente entity);

    // Después de mapear el cliente, añadimos el sub‐dto de persona
    @AfterMapping
    default void fillPersona(@MappingTarget ClienteResponseDTO dto, Cliente entity) {
        Persona p = entity;
        dto.setPersona(new PersonaDTO(
                p.getId(),
                p.getNombre(),
                p.getGenero(),
                p.getEdad(),
                p.getIdentificacion(),
                p.getDireccion(),
                p.getTelefono()));
    }
}