# OBTENER DESCARGABLES

```
    https://github.com/MLahuasi/reto-senior-ntt-data.git
```

# AMBIENTE

1. Se desarrollo con la version de Java

```
    openjdk 20.0.2 2023-07-18
    OpenJDK Runtime Environment (build 20.0.2+9-78)
    OpenJDK 64-Bit Server VM (build 20.0.2+9-78, mixed mode, sharing)
```

2. El IDE de desarrollo fue VSCode. **NOTA**: Visual Studio Code permitió usar la versión 17 (revisar archivos pom.xml)

# DISTRIBUCION ENTREGABLES

El reto contiene los siguientes directorios:

```
└─ cliente-service
└─ cuenta-service
└─ environment
```

Donde:

- environment: Genera un contenedor con la BDD Postgres y Rabbit (Operaciones Async entre microservicios). Además crea tablas mediante archivo BaseDatos.sql
- cliente-service: Fuentes microservicio #1

```
cliente-service/
├── pom.xml *
├── Dockerfile (p)
├── .dockerignore (p)
├── src/
│   ├── main/
│   │   ├── java/com/jmlq/cliente_service/
│   │   │   ├── controller/
│   │   │   │   └── ClienteController.java *
│   │   │   ├── dto/
│   │   │   │   ├── ClienteCreateDTO.java *
│   │   │   │   ├── ClienteDeleteDTO.java *
│   │   │   │   ├── ClienteReadDTO.java *
│   │   │   │   ├── ClienteResponseDTO.java *
│   │   │   │   └── ClienteUpdateDTO.java *
│   │   │   ├── mapper/
│   │   │   │   └── ClienteMapper.java *
│   │   │   ├── model/
│   │   │   │   ├── Persona.java *
│   │   │   │   └── Cliente.java *
│   │   │   ├── repository/
│   │   │   │   └── ClienteRepository.java *
│   │   │   ├── service/
│   │   │   │   ├── ClienteService.java *
│   │   │   │   └── impl/
│   │   │   │       └── ClienteServiceImpl.java *
│   │   │   ├── config/
│   │   │   │   └── RabbitConfig.java (p)
│   │   │   └── event/
│   │   │       └── ClienteCreatedEvent.java (p)
│   ├── test/
│   │   └── java/com/jmlq/cliente_service/
│   │       ├── ClienteServiceTest.java (p)
│   │       └── ClienteControllerTest.java (p)
│   └── resources/
│       ├── application.yml *
│       └── application-test.yml (p)
└── README.md (p)
```

> cuenta-service: Fuentes microservicio #2

```

cuenta-service/
├── pom.xml *
├── Dockerfile (p)
├── .dockerignore (p)
├── src/
│   ├── main/
│   │   ├── java/com/jmlq/cuenta_service/
│   │   │   ├── controller/
│   │   │   │   └── CuentaController.java (p)
│   │   │   ├── dto/
│   │   │   │   ├── CuentaCreateDTO.java *
│   │   │   │   ├── CuentaReadDTO.java *
│   │   │   │   ├── MovimientoCreateDTO.java *
│   │   │   │   └── MovimientoReadDTO.java *
│   │   │   ├── mapper/
│   │   │   │   ├── CuentaMapper.java *
│   │   │   │   └── MovimientoMapper.java *
│   │   │   ├── model/
│   │   │   │   ├── Cuenta.java *
│   │   │   │   └── Movimiento.java *
│   │   │   ├── repository/
│   │   │   │   ├── CuentaRepository.java *
│   │   │   │   └── MovimientoRepository.java *
│   │   │   ├── service/
│   │   │   │   ├── CuentaService.java (p)
│   │   │   │   ├── MovimientoService.java (p)
│   │   │   │   └── impl/
│   │   │   │       ├── CuentaServiceImpl.java (p)
│   │   │   │       └── MovimientoServiceImpl.java (p)
│   │   │   ├── config/
│   │   │   │   └── RabbitConfig.java (p)
│   │   │   └── event/
│   │   │       ├── CuentaCreatedEvent.java (p)
│   │   │       └── MovimientoCreatedEvent.java (p)
│   ├── test/
│   │   └── java/com/jmlq/cuenta_service/
│   │       ├── CuentaServiceTest.java (p)
│   │       └── CuentaControllerTest.java (p)
│   └── resources/
│       ├── application.yml *
│       └── application-test.yml (p)
└── README.md (p)

```

## Levantar Imagen Docker BDD y Rabbit

1. Ingresar al directorio [environment](./environment/)
2. Ejecutar el comando:

```
    docker compose up -d
```

3. Se crea contenedor `Docker` con imagenes: `Postgres` y `Rabbit`

   ![](./assets/1-docker-environment.png)

4. Además se crean las tablas en la BDD

   ![](./assets/2-create-tables.png)

## EJECUTAR PROYECTOS

#### [Ejecutar cliente-service](./cliente-service/README.md)

#### [Ejecutar cuenta-service](./cuenta-service/README.md)

## STATUS DESARROLLO

| Servicio            | Funcionalidad                                                       | Estado |
| ------------------- | ------------------------------------------------------------------- | :----: |
| **Cliente-Service** | CRUD de clientes (POST, GET, PUT, DELETE)                           |   \*   |
| Cliente-Service     | Mapear DTO ↔ Entidad (`ClienteCreateDTO`, `ClienteReadDTO`…)        |   \*   |
| Cliente-Service     | Repositorio JPA (`ClienteRepository`)                               |   \*   |
| Cliente-Service     | Capa de servicio (`ClienteService` + `ClienteServiceImpl`)          |   \*   |
| Cliente-Service     | Controller REST (`ClienteController`)                               |   \*   |
| Cliente-Service     | Persistencia con Spring Data JPA y PostgreSQL                       |   \*   |
| Cliente-Service     | Configuración de propiedades en `application.yml`                   |   \*   |
| Cliente-Service     | README con instrucciones generales                                  |   \*   |
| Cliente-Service     | Dockerfile                                                          |  (p)   |
| Cliente-Service     | `.dockerignore`                                                     |  (p)   |
| Cliente-Service     | Configuración RabbitMQ (`RabbitConfig`)                             |   \*   |
| Cliente-Service     | Definición de eventos (ej. `ClienteCreatedEvent`)                   |   \*   |
| Cliente-Service     | Publicación de eventos al crear                                     |   \*   |
| Cliente-Service     | Consumo de eventos con `@RabbitListener`                            |   \*   |
| Cliente-Service     | Swagger/OpenAPI (`springdoc-openapi`)                               |   \*   |
| Cliente-Service     | Pruebas unitarias (servicio + controlador)                          |   \*   |
| Cliente-Service     | Pruebas de integración (Testcontainers o BD embebida)               |   \*   |
| Cliente-Service     | Validaciones con `@Valid` y Bean Validation en DTOs                 |   \*   |
| **Cuenta-Service**  | CRUD de cuentas (POST, GET, PUT, DELETE)                            |   \*   |
| Cuenta-Service      | CRUD de movimientos (POST, GET)                                     |   \*   |
| Cuenta-Service      | Mapear DTO ↔ Entidad (`CuentaCreateDTO`, `MovimientoReadDTO`…)      |   \*   |
| Cuenta-Service      | Repositorios JPA (`CuentaRepository`, `MovimientoRepository`)       |   \*   |
| Cuenta-Service      | Persistencia con Spring Data JPA y PostgreSQL                       |   \*   |
| Cuenta-Service      | Configuración de propiedades en `application.yml`                   |   \*   |
| Cuenta-Service      | Dockerfile                                                          |  (p)   |
| Cuenta-Service      | `.dockerignore`                                                     |  (p)   |
| Cuenta-Service      | Configuración RabbitMQ (`RabbitConfig`)                             |   \*   |
| Cuenta-Service      | Definición de eventos (`ClienteCreatedEvent`)                       |   \*   |
| Cuenta-Service      | Consumo de eventos con `@RabbitListener`                            |   \*   |
| Cuenta-Service      | Servicios (`CuentaService`, `MovimientoService`) e implementaciones |   \*   |
| Cuenta-Service      | Controller REST (`CuentaController`)                                |   \*   |
| Cuenta-Service      | Swagger/OpenAPI (`springdoc-openapi`)                               |   \*   |
| Cuenta-Service      | Validaciones con `@Valid` y Bean Validation en DTOs                 |   \*   |
| Cuenta-Service      | README específico del servicio                                      |   \*   |

# FUNCIONES

## COMUNICACION ASYNC

Como no se especificó en el documento técnico como debía ser implementado el proceso `async` usando `Rabbit`, se implementó lo siguiente:

Cuando se crea un `Cliente` (cliente-service) envía `ClienteId`, `NumeoCuenta`, `SaldoInicial`, `TipoCuenta` hacia `Cuenta` (cuenta-service) mendiante `Rabbit`.

```
                                     Rabbit


                                    ClienteId
                                    NumeoCuenta
                                    SaldoInicial
                                    TipoCuenta
                                ------------------>
            Cliente (Create)                           Cuenta

```

Ejecutar:

**POST**

```
    http://localhost:8081/api/v1/clientes
```

## F1 CRUD

## F2 y F3 (Registro de movimientos y Alerta “Saldo no disponible”)

**POST**

```
    http://localhost:8082/api/v1/movimientos
```

![](./assets/6-F2-F3.png)

## F4 (Reportes: Generar un reporte de “Estado de cuenta” especificando un rango de fechas y cliente.)

**GET**

```
    http://localhost:8082/api/v1/reportes/estado-cuenta?clienteId=18&fechaIni=2025-05-10&fechaFin=2025-05-11
```

![](./assets/7-F4-EstadoCuenta.png)

## F5 - Pruebas unitarias: Implementar 1 prueba unitaria para la entidad de dominio Cliente

[ClienteTest](./cliente-service/src/test/java/com/jmlq/cliente_service/model/ClienteTest.java)

![](./assets/8-F5-PruebaUnitaria.png)

## F6 - Pruebas de Integración: Implementar 1 prueba de integración

[](./cliente-service/src/test/java/com/jmlq/cliente_service/ClienteIntegrationTest.java)

![](./assets/9-F6-PruebaIntegracion.png)

## F8 - Realizar la documentación con Swagger mediante OpenAPI

**NOTA**: Se presentó alguna incompatiblidad con la dependencia que produce un error que se expone con el contról centralizado de Exceptions [GlobalExceptionHandler](./cliente-service/src/main/java/com/jmlq/cliente_service/exception/GlobalExceptionHandler.java).

```
    <dependency>
      <groupId>org.springdoc</groupId>
      <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
      <version>2.0.2</version>
    </dependency>
```

La clase `GlobalExceptionHandler` también permite capturar las excepciones que se generan en las validaciones de los DTOs, por lo que opté por controlar la exception que se produce con Swagger y no sacrificar la visualización de mensajes de error personalizado.

### SWAGGER sin control de Exceptions

**Cliente-Service**: http://localhost:8081/api/v1/swagger-ui.html

![](./assets/4-swagger.png)

**Cuenta-Service**: http://localhost:8082/api/v1/swagger-ui.html

![](./assets/5-swagger.png)

### SWAGGER con control de Exceptions

**Cliente-Service**: http://localhost:8081/api/v1/swagger-ui.html

![](./assets/4-1-swagger.png)

**Cuenta-Service**: http://localhost:8082/api/v1/swagger-ui.html

![](./assets/5-1-swagger.png)
