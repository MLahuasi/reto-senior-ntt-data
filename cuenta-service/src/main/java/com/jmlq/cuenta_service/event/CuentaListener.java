package com.jmlq.cuenta_service.event;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.jmlq.cuenta_service.repository.CuentaRepository;
import com.jmlq.cuenta_service.service.CuentaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// @Component
// @Slf4j
// @RequiredArgsConstructor
// public class CuentaListener {

// private final CuentaService cuentaService;

// @RabbitListener(queues = "${rabbitmq.queue.clienteCreated}", containerFactory
// = "rabbitListenerContainerFactory")
// public void onClienteCreated(ClienteCreatedEvent event) {
// if (event.getClienteId() == null) {
// log.warn("Evento ignorado: clienteId nulo => {}", event);

// }

// // Imprimir el evento recibido en consola
// System.out.println("Id Recibido en CuentaService: " + event.getClienteId());

// cuentaService.createDefaultAccountForClient(event.getClienteId(),
// event.getNumeroCuenta(),
// event.getSaldoInicial(), event.getTipoCuenta());
// }
// }

@Component
@Slf4j
@RequiredArgsConstructor
public class CuentaListener {

    private final CuentaService cuentaService;
    private final CuentaRepository cuentaRepository; // inyecta también el repo

    @RabbitListener(queues = "${rabbitmq.queue.clienteCreated}", containerFactory = "rabbitListenerContainerFactory")
    public void onClienteCreated(ClienteCreatedEvent event) {
        Long clienteId = event.getClienteId();
        String nro = event.getNumeroCuenta();

        if (cuentaRepository.existsByClienteId(clienteId)) {
            log.info("Cuenta ya existe para clienteId={} (nro={}), ignoro evento", clienteId, nro);
            return;
        }

        try {
            cuentaService.createDefaultAccountForClient(
                    clienteId, nro, event.getSaldoInicial(), event.getTipoCuenta());
        } catch (DataIntegrityViolationException ex) {
            log.warn("Intento duplicado de crear cuenta nro={}, {}", nro, ex.getMessage());
        }
    }
}