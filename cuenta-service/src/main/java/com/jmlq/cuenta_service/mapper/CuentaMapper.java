package com.jmlq.cuenta_service.mapper;

import com.jmlq.cuenta_service.dto.*;
import com.jmlq.cuenta_service.model.Cuenta;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CuentaMapper {

    // --- CREATE
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "numeroCuenta", target = "numeroCuenta")
    @Mapping(source = "tipoCuenta", target = "tipoCuenta")
    @Mapping(source = "saldoInicial", target = "saldoInicial")
    @Mapping(source = "estado", target = "estado")
    Cuenta toEntity(CuentaCreateDTO dto);

    // --- READ
    @Mapping(source = "id", target = "id")
    @Mapping(source = "numeroCuenta", target = "numeroCuenta")
    @Mapping(source = "tipoCuenta", target = "tipoCuenta")
    @Mapping(source = "saldoInicial", target = "saldoInicial")
    @Mapping(source = "estado", target = "estado")
    CuentaReadDTO toReadDto(Cuenta entity);

    // --- UPDATE
    @Mapping(source = "id", target = "id")
    @Mapping(source = "numeroCuenta", target = "numeroCuenta")
    @Mapping(source = "tipoCuenta", target = "tipoCuenta")
    @Mapping(source = "saldoInicial", target = "saldoInicial")
    @Mapping(source = "estado", target = "estado")
    Cuenta toEntity(CuentaUpdateDTO dto);

    // --- DELETE
    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "id", target = "id")
    Cuenta toEntity(CuentaDeleteDTO dto);

    // --- RESPONSE
    @Mapping(source = "numeroCuenta", target = "numeroCuenta")
    @Mapping(source = "tipoCuenta", target = "tipoCuenta")
    @Mapping(source = "saldoInicial", target = "saldoInicial")
    @Mapping(source = "estado", target = "estado")
    CuentaResponseDTO toResponse(Cuenta entity);
}