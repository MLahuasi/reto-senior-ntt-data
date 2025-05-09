package com.jmlq.cliente_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jmlq.cliente_service.dto.ClienteCreateDTO;
import com.jmlq.cliente_service.dto.ClienteReadDTO;
import com.jmlq.cliente_service.dto.ClienteResponseDTO;
import com.jmlq.cliente_service.dto.ClienteUpdateDTO;
import com.jmlq.cliente_service.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO create(@RequestBody ClienteCreateDTO dto) {
        return clienteService.create(dto);
    }

    @GetMapping("/{id}")
    public ClienteResponseDTO getById(@PathVariable Long id) {
        return clienteService.findById(id);
    }

    @GetMapping
    public List<ClienteReadDTO> getAll() {
        return clienteService.findAll();
    }

    @PutMapping("/{id}")
    public ClienteResponseDTO update(
            @PathVariable Long id,
            @RequestBody ClienteUpdateDTO dto) {
        dto.setPersonaId(id); // asegúrate de propagar el id
        return clienteService.update(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        clienteService.delete(id);
    }
}