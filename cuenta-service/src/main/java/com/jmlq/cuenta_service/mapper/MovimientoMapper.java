package com.jmlq.cuenta_service.mapper;

import com.jmlq.cuenta_service.dto.*;
import com.jmlq.cuenta_service.model.Movimiento;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {

    // --- CREATE
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "fecha", target = "fecha")
    @Mapping(source = "tipoMovimiento", target = "tipoMovimiento")
    @Mapping(source = "valor", target = "valor")
    // saldo se calcula en el servicio, no viene en el DTO de creación:
    @Mapping(target = "saldo", ignore = true)
    Movimiento toEntity(MovimientoCreateDTO dto);

    // --- READ
    @Mapping(source = "id", target = "id")
    @Mapping(source = "fecha", target = "fecha")
    @Mapping(source = "tipoMovimiento", target = "tipoMovimiento")
    @Mapping(source = "valor", target = "valor")
    @Mapping(source = "saldo", target = "saldo")
    MovimientoReadDTO toReadDto(Movimiento entity);

    // --- UPDATE
    @Mapping(source = "id", target = "id")
    @Mapping(source = "fecha", target = "fecha")
    @Mapping(source = "tipoMovimiento", target = "tipoMovimiento")
    @Mapping(source = "valor", target = "valor")
    @Mapping(source = "saldo", target = "saldo")
    Movimiento toEntity(MovimientoUpdateDTO dto);

    // --- DELETE
    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "id", target = "id")
    Movimiento toEntity(MovimientoDeleteDTO dto);

    // --- RESPONSE
    @Mapping(source = "id", target = "id")
    @Mapping(source = "fecha", target = "fecha")
    @Mapping(source = "tipoMovimiento", target = "tipoMovimiento")
    @Mapping(source = "valor", target = "valor")
    @Mapping(source = "saldo", target = "saldo")
    MovimientoResponseDTO toResponse(Movimiento entity);
}