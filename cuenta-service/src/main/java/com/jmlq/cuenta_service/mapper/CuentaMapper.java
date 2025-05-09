package com.jmlq.cuenta_service.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.jmlq.cuenta_service.dto.CuentaCreateRequestDTO;
import com.jmlq.cuenta_service.dto.CuentaResponseDTO;
import com.jmlq.cuenta_service.model.Cuenta;

@Mapper(componentModel = "spring")
public interface CuentaMapper {

    // request DTO → entidad
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "movimientos", ignore = true)
    Cuenta toEntity(CuentaCreateRequestDTO dto);

    // entidad → response DTO
    // el campo clienteNombre lo rellenaremos en el service, así que lo marcamos
    // como expresión o le ponemos ignore
    @Mapping(target = "clienteNombre", ignore = true)
    CuentaResponseDTO toResponse(Cuenta cuenta);

    // Para actualizar un entity si lo necesitaras
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "movimientos", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(CuentaCreateRequestDTO dto, @MappingTarget Cuenta entity);
}