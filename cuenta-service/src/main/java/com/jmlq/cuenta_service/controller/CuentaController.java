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
import com.jmlq.cuenta_service.service.CuentaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cuentas")
@Validated
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    // F1: CRUDs
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CuentaReadDTO create(@Valid @RequestBody CuentaCreateDTO dto) {
        return cuentaService.create(dto);
    }

    // F1: CRUDs
    @GetMapping("/{id}")
    public CuentaReadDTO getById(@PathVariable Long id) {
        return cuentaService.getById(id);
    }

    @GetMapping
    public List<CuentaReadDTO> getAll() {
        return cuentaService.getAll();
    }

    // F1: CRUDs
    @PutMapping("/{id}")
    public CuentaReadDTO update(
            @PathVariable Long id,
            @Valid @RequestBody CuentaUpdateDTO dto) {
        dto.setId(id);
        return cuentaService.update(dto);
    }

    // F1: CRUDs
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cuentaService.delete(id);
    }

}
