# ARQUITECTURAS

## MICROSERVICIOS

- Una aplicación se descompone en servicios pequeños, independientes y desplegables de forma autónoma.

#### COMUNICACION ASÍNCRONA ENTRE MICROSERVCIOS

El servicio A envía un mensaje a un intermediario (como RabbitMQ) sin esperar respuesta inmediata; B lo procesa cuando está listo.

##### VENTAJAS

> - Mayor tolerancia a fallos y picos de carga (buffering).
> - Desacopla tiempos de procesamiento.

##### CUÁNDO USARLA

> - Procesos de larga duración (envío de correos, generación de reportes).
> - Pipelines de datos o integración con sistemas externos.

##### CUÁNDO NO USARLA

> - Operaciones que requieren respuesta inmediata (login, cálculos críticos) → mejor REST sincrónico o gRPC.

#### CONTRATO DE MENSAJE

Un contrato de mensaje es la estructura y esquema acordado que deben cumplir los mensajes intercambiados:

> - Definición de propiedades (nombres, tipos, obligatorios).
> - Versionado: v1, v2… para compatibilidad.
> - Garantiza que productor y consumidor «hablen el mismo idioma».

#### EXCHANGE / ROUTING KEY

> - **EXCHANGE**: componente de RabbitMQ que recibe mensajes de productores y los enruta a colas según reglas (Tipos: direct, topic, fanout, headers)
> - **ROUTING KEY**: Cadena que el productor adjunta al mensaje para que la exchange determine a qué cola(s) enviarlo. Ejemplo: exchange orders.exchange + routing key order.created.us → colas suscritas a order.created.

## CAPAS

- Domain (@Entity)
- Persistece (\*Repository)
- Service (\*Service)
- Presentation (@RestController)

## EVENT-DRIVEN

- Los servicios intercambian eventos a través de RabbitMQ para desacoplar sus ciclos de vida y lograr mayor resiliencia

## DOMAIN DRIVE DESING

- Domain (Entidades)
- Application (Interfaces) (reglas de negocio) capa media
- Infrastructure (Externos)
- Presentation (api)

## CLEAN ARCHITECTURE (HEXAGONAL)

- Domain (entidades) capa nucleo
- Application (Interfaces) (reglas de negocio) capa media
- Persistence: (Persistencia) capa superior
- Infrastructura: (Externos) capa superior
- \*Servicios: (Lógica de negocio)
- Presentation: (api) capa superior

## CORS

- Separa Query (No afectan) de Commands (afectan) a la base de datos.
- Usa el Patron Mediator (Servicios y Presentación)

# DESING PATTERNS

### MEDIATOR (Design Pattern)

- Evita referencias entre objetos.

### UNIT OF WORK (Desing Pattern)

- Transacciones de BDD

### SPECIFICATION (Desing Pattern)

- Definir Validaciones True/False

### REPOSITORY

- Oculta detalles de la persistencia (JPA, EF Core, MongoDB).

### SERVICE LAYER

- Centraliza y separa la lógica de negocio de la API o controlador

### DATA MAPPER

- Uso de MapStruct (ClienteMapper) para convertir entre entidades y DTOs de forma declarativa

### FACTORY

- Configuración de beans de RabbitMQ con QueueBuilder, DirectExchange y BindingBuilder en RabbitConfig.

### OBSERVER / EVENT LISTENER

- @RabbitListener en CuentaListener para suscribirse a eventos de creación de cliente.

### DEPENDENCY INJECTION / INVERSION OF CONTROL

- Spring gestiona la creación e inyección de todos los beans.

## EN CODIGO

### Comunicación asíncrona con RabbitMQ

#### cliente-service

1. **PUBLICACION** (ClienteServiceImpl)

```
rabbitTemplate.convertAndSend(
  exchange,                // "${rabbitmq.exchange.cliente}"
  routingKey,              // "${rabbitmq.routingkey.cliente.created}"
  event                    // instancia de ClienteCreatedEvent
);
```

2. **CONFIGURACIÓN** (RabbitConfig.java)

```
@Bean DirectExchange clienteExchange(@Value("${rabbitmq.exchange.cliente}") String name) { … }
@Bean Queue clienteCreatedQueue() { return QueueBuilder.durable("cliente.created.queue").build(); }
@Bean Binding bindingClienteCreated(Queue q, DirectExchange ex) {
  return BindingBuilder.bind(q).to(ex).with("${rabbitmq.routingkey.cliente.created}");
}
```

3. **CONTRATO DE MENSAJE** (ClienteCreatedEvent implementa Serializable)

```
Long clienteId;
String numeroCuenta;
BigDecimal saldoInicial;
String tipoCuenta;
```

#### cuenta-service

1. **CONSUMO** (CuentaListener)

```
@RabbitListener(queues = "${rabbitmq.queue.clienteCreatedQueue}")
public void onClienteCreated(ClienteCreatedEvent event) { … }

El listener crea una cuenta por defecto usando

cuentaService.createDefaultAccountForClient(...).
```

# TOOLS

## RABBITMQ

Es un broker de mensajes basado en el protocolo AMQP. Su función principal es:

> - Desacoplar productores (quienes envían mensajes) de consumidores (quienes los procesan).
> - Gestionar colas, garantizando entrega fiable (ack/nack), reintentos y balanceo de carga.
> - Soportar patrones como publish/subscribe, work queues y RPC asíncrono.

Comparación :
.NET: MassTransit, Raw RabbitMQ.Client;
Java: Spring Boot, spring-boot-starter-amqp y RabbitTemplate/@RabbitListener.

######

13. Rendimiento, Escalabilidad y Resiliencia
    Java: Resilience4j (Circuit Breaker, Retry, Bulkhead) vs Polly (.NET).

Monitorización: Actuator, métricas, health checks.

14. DevOps Básico
    CI/CD: GitHub Actions o Azure DevOps Pipelines para compilar, testear, dockerizar y desplegar.

Quality Gates: SonarCloud / SonarQube.
