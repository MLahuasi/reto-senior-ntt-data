package com.jmlq.cuenta_service.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody; // <-- Import correcto
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.jmlq.cuenta_service.dto.CuentaCreateDTO;
import com.jmlq.cuenta_service.dto.CuentaReadDTO;
import com.jmlq.cuenta_service.dto.CuentaUpdateDTO;
import com.jmlq.cuenta_service.dto.MovimientoReadDTO; // <-- DTO de movimiento
import com.jmlq.cuenta_service.service.CuentaService;
import com.jmlq.cuenta_service.service.MovimientoService; // <-- Servicio de movimientos

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cuentas")
@Validated
public class CuentaController {

    private final CuentaService cuentaService;
    private final MovimientoService movimientoService;

    public CuentaController(CuentaService cuentaService,
            MovimientoService movimientoService) {
        this.cuentaService = cuentaService;
        this.movimientoService = movimientoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CuentaReadDTO create(@Valid @RequestBody CuentaCreateDTO dto) {
        return cuentaService.create(dto);
    }

    @GetMapping("/{id}")
    public CuentaReadDTO getById(@PathVariable Long id) {
        return cuentaService.getById(id);
    }

    @GetMapping
    public List<CuentaReadDTO> getAll() {
        return cuentaService.getAll();
    }

    @PutMapping("/{id}")
    public CuentaReadDTO update(
            @PathVariable Long id,
            @Valid @RequestBody CuentaUpdateDTO dto) {
        dto.setId(id);
        return cuentaService.update(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cuentaService.delete(id);
    }

    // ——————————————
    // Nuevo endpoint para listar movimientos de esta cuenta
    @GetMapping("/{id}/movimientos")
    public List<MovimientoReadDTO> getMovimientosByCuenta(@PathVariable Long id) {
        // Se delega en el servicio de movimientos
        return movimientoService.getByCuenta(id);
    }
}
