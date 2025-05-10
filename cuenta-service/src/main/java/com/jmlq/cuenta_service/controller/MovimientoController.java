package com.jmlq.cuenta_service.controller;

import org.springframework.http.HttpStatus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jmlq.cuenta_service.dto.MovimientoCreateDTO;
import com.jmlq.cuenta_service.dto.MovimientoReadDTO;
import com.jmlq.cuenta_service.dto.MovimientoUpdateDTO;
import com.jmlq.cuenta_service.service.MovimientoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/movimientos")
@Validated
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovimientoReadDTO create(@Valid @RequestBody MovimientoCreateDTO dto) {
        return movimientoService.create(dto);
    }

    @PutMapping("/{id}")
    public MovimientoReadDTO update(
            @PathVariable Long id,
            @Valid @RequestBody MovimientoUpdateDTO dto) {
        dto.setId(id);
        return movimientoService.update(dto);
    }

    @GetMapping("/{id}")
    public MovimientoReadDTO getById(@PathVariable Long id) {
        return movimientoService.getById(id);
    }

    @GetMapping
    public List<MovimientoReadDTO> getAll() {
        return movimientoService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        movimientoService.delete(id);
    }

    @GetMapping("/cuenta/{cuentaId}")
    public List<MovimientoReadDTO> getByCuenta(@PathVariable Long cuentaId) {
        return movimientoService.getByCuenta(cuentaId);
    }
}