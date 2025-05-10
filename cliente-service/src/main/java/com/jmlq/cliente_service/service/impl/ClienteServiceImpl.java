package com.jmlq.cliente_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jmlq.cliente_service.dto.ClienteCreateDTO;
import com.jmlq.cliente_service.dto.ClienteReadDTO;
import com.jmlq.cliente_service.dto.ClienteResponseDTO;
import com.jmlq.cliente_service.dto.ClienteUpdateDTO;
import com.jmlq.cliente_service.mapper.ClienteMapper;
import com.jmlq.cliente_service.model.Cliente;
import com.jmlq.cliente_service.repository.ClienteRepository;
import com.jmlq.cliente_service.service.ClienteService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ClienteServiceImpl(ClienteRepository clienteRepository,
            ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public ClienteResponseDTO create(ClienteCreateDTO dto) {
        // 1) Mapear DTO → entidad Cliente (incluye campos de Persona gracias a
        // herencia)
        // Encriptar la contraseña antes de guardar
        dto.setContrasena(passwordEncoder.encode(dto.getContrasena()));
        Cliente cliente = clienteMapper.toEntity(dto);

        // 2) Guardar Cliente; Hibernate insertará en persona y luego en cliente
        Cliente saved = clienteRepository.save(cliente);

        // 3) Mapear entidad guardada → DTO de respuesta
        return clienteMapper.toResponse(saved);
    }

    @Override
    public ClienteResponseDTO findById(Long id) {
        Cliente entity = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe cliente " + id));
        return clienteMapper.toResponse(entity);
    }

    @Override
    public List<ClienteReadDTO> findAll() {
        return clienteRepository.findAll().stream()
                .map(clienteMapper::toReadDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ClienteResponseDTO update(ClienteUpdateDTO dto) {
        // 1) Recuperar la entidad que ya existe
        Cliente existing = clienteRepository.findById(dto.getPersonaId())
                .orElseThrow(() -> new EntityNotFoundException("No existe cliente con id " + dto.getPersonaId()));

        // 2) Copiar los campos del DTO sobre la entidad recuperada
        // Para esto puedes usar MapStruct con un método @BeanMapping(ignoreNull =
        // true):
        dto.setContrasena(passwordEncoder.encode(dto.getContrasena()));
        clienteMapper.updateEntityFromDto(dto, existing);

        // 3) Guardar (Hibernate hace UPDATE porque existing.id != null)
        Cliente saved = clienteRepository.save(existing);

        // 4) Devolver DTO de respuesta
        return clienteMapper.toResponse(saved);
    }

    @Override
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
}