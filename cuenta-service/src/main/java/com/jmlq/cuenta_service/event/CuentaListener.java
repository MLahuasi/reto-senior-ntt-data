package com.jmlq.cuenta_service.event;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.jmlq.cuenta_service.service.CuentaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class CuentaListener {

    private final CuentaService cuentaService;

    @RabbitListener(queues = "${rabbitmq.queue.clienteCreated}", containerFactory = "rabbitListenerContainerFactory")
    public Long onClienteCreated(ClienteCreatedEvent event) {
        if (event.getClienteId() == null) {
            log.warn("Evento ignorado: clienteId nulo => {}", event);
            return 0L;
        }

        // Imprimir el evento recibido en consola
        System.out.println("Id Recibido en CuentaService: " + event.getClienteId());

        return cuentaService.createDefaultAccountForClient(event.getClienteId(), event.getNumeroCuenta(),
                event.getSaldoInicial(), event.getTipoCuenta());
    }
}