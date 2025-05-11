package com.jmlq.cliente_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jmlq.cliente_service.dto.ClienteCreateDTO;
import com.jmlq.cliente_service.dto.ClienteReadDTO;
import com.jmlq.cliente_service.dto.ClienteResponseDTO;
import com.jmlq.cliente_service.dto.ClienteUpdateDTO;
import com.jmlq.cliente_service.event.ClienteCreatedEvent;
import com.jmlq.cliente_service.mapper.ClienteMapper;
import com.jmlq.cliente_service.model.Cliente;
import com.jmlq.cliente_service.repository.ClienteRepository;
import com.jmlq.cliente_service.service.ClienteService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

    private final RabbitTemplate rabbitTemplate;
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Value("${rabbitmq.exchange.cliente}")
    private String exchange;
    @Value("${rabbitmq.routingkey.cliente.created}")
    private String routingKey;

    public ClienteServiceImpl(ClienteRepository clienteRepository,
            ClienteMapper clienteMapper, RabbitTemplate rabbitTemplate) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
        this.rabbitTemplate = rabbitTemplate;
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

        // 3) Publicar evento asincrónico notificando creación
        System.out.println("Cliente creado con id: " + saved.getId());
        ClienteCreatedEvent event = new ClienteCreatedEvent(saved.getId(), dto.getNumeroCuenta(),
                dto.getSaldoInicial(), dto.getTipoCuenta());
        // rabbitTemplate.convertAndSend(exchange, routingKey, event);
        Object reply = rabbitTemplate.convertSendAndReceive(exchange, routingKey, event);
        Long cuentaId = null;
        if (reply instanceof Integer) {
            cuentaId = ((Integer) reply).longValue();
        } else if (reply instanceof Long) {
            cuentaId = (Long) reply;
        } else {
            cuentaId = 0L;
        }

        // 4) Mapear entidad guardada → DTO de respuesta
        var resp = clienteMapper.toResponse(saved);
        resp.setCuentaId(cuentaId);
        // imprime en consolo el id de la cuenta
        System.out.println("Id de cuenta: " + cuentaId);
        return resp;
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