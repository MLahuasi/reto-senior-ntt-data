package com.jmlq.cliente_service.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.jmlq.cliente_service.dto.ClienteCreateDTO;
import com.jmlq.cliente_service.dto.ClienteReadDTO;
import com.jmlq.cliente_service.dto.ClienteResponseDTO;
import com.jmlq.cliente_service.dto.ClienteUpdateDTO;
import com.jmlq.cliente_service.dto.PersonaDTO;
import com.jmlq.cliente_service.model.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    // CREATE
    @Mapping(target = "id", ignore = true)
    Cliente toEntity(ClienteCreateDTO dto);

    // READ
    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "id", target = "persona.id")
    @Mapping(source = "nombre", target = "persona.nombre")
    @Mapping(source = "genero", target = "persona.genero")
    @Mapping(source = "edad", target = "persona.edad")
    ClienteReadDTO toReadDto(Cliente cliente);

    // UPDATE
    @Mapping(source = "personaId", target = "id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ClienteUpdateDTO dto, @MappingTarget Cliente cliente);

    // --- RESPONSE con sub-dto persona
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "persona", ignore = true)
    ClienteResponseDTO toResponse(Cliente cliente);

    @AfterMapping
    default void fillPersona(
            @MappingTarget ClienteResponseDTO dto,
            Cliente cliente) {
        dto.setPersona(new PersonaDTO(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getGenero(),
                cliente.getEdad(),
                cliente.getIdentificacion(),
                cliente.getDireccion(),
                cliente.getTelefono()));
        dto.setEstado(cliente.getEstado());
    }
}
