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
├── Dockerfile (pendiente)
├── .dockerignore (pendiente)
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
│   │   │   │   ├── ClienteUpdateDTO.java *
│   │   │   │   ├── PersonaDTO.java *
│   │   │   ├── mapper/
│   │   │   │   └── ClienteMapper.java *
│   │   │   ├── model/
│   │   │   │   ├── Cliente.java *
│   │   │   │   └── Persona.java *
│   │   │   ├── repository/
│   │   │   │   └── ClienteRepository.java *
│   │   │   ├── service/
│   │   │   │   ├── ClienteService.java *
│   │   │   │   └── impl/
│   │   │   │       └── ClienteServiceImpl.java *
│   │   │   ├── event/
│   │   │   │   ├── ClienteCreated.java (pendiente)
│   │   │   │   ├── ClienteRead.java (pendiente)
│   │   │   │   ├── ClienteUpdate.java (pendiente)
│   │   │   │   └── ClienteDelete.java (pendiente)
│   │   │   └── config/
│   │   │       ├── RabbitConfig.java (pendiente)
│   │   │       └── SwaggerConfig.java (opcional, pendiente)
│   │   └── resources/
│   │       └── application.yml *
│   └── test/
│       └── java/com/jmlq/cliente_service/
│           ├── service/ClienteServiceTest.java (pendiente)
│           └── controller/ClienteControllerTest.java (pendiente)
└── README.md (pendiente documentación de uso)

```

> cuenta-service: Fuentes microservicio #2

```

cuenta-service/
├── pom.xml *
├── Dockerfile (pendiente)
├── .dockerignore (pendiente)
├── src/
│   ├── main/
│   │   ├── java/com/jmlq/cuenta_service/
│   │   │   ├── controller/
│   │   │   │   └── CuentaController.java (pendiente)
│   │   │   ├── dto/
│   │   │   │   ├── CuentaCreateDTO.java *
│   │   │   │   ├── CuentaDeleteDTO.java *
│   │   │   │   ├── CuentaReadDTO.java *
│   │   │   │   ├── CuentaResponseDTO.java *
│   │   │   │   ├── CuentaUpdateDTO.java *
│   │   │   │   ├── MovimientoCreateDTO.java *
│   │   │   │   ├── MovimientoDeleteDTO.java *
│   │   │   │   ├── MovimientoReadDTO.java *
│   │   │   │   └── MovimientoResponseDTO.java *
│   │   │   │   ├── MovimientoUpdateDTO.java *
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
│   │   │   │   ├── CuentaService.java (pendiente)
│   │   │   │   ├── MovimientoService.java (pendiente)
│   │   │   │   └── impl/
│   │   │   │       ├── CuentaServiceImpl.java (pendiente)
│   │   │   │       └── MovimientoServiceImpl.java (pendiente)
│   │   │   ├── event/
│   │   │   │   ├── CuentaCreated.java (pendiente)
│   │   │   │   ├── CuentaRead.java (pendiente)
│   │   │   │   ├── CuentaUpdate.java (pendiente)
│   │   │   │   ├── CuentaDelete.java (pendiente)
│   │   │   │   ├── MovimientoCreated.java (pendiente)
│   │   │   │   ├── MovimientoRead.java (pendiente)
│   │   │   │   ├── MovimientoUpdate.java (pendiente)
│   │   │   │   └── MovimientoDelete.java (pendiente)
│   │   │   └── config/
│   │   │       ├── RabbitConfig.java (pendiente)
│   │   │       └── SwaggerConfig.java (opcional, pendiente)
│   │   └── resources/
│   │       └── application.yml *
│   └── test/
│       └── java/com/jmlq/cuenta_service/
│           ├── service/CuentaServiceTest.java (pendiente)
│           └── controller/CuentaControllerTest.java (pendiente)
└── README.md (pendiente documentación de uso)

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

### cliente-service

1. Ingresar al directorio [cliente-service](./cliente-service/) por consola
2. Ejecutar

```
    mvn clean spring-boot:run
```

### cuenta-service

1. Ingresar al directorio [cuenta-service](./cuenta-service/) por consola
2. Ejecutar

```
    mvn clean spring-boot:run
```

## ENDPOINTS

Para las pruebas se uso Postman.
Las solicitudes se las realiza con: Body / raw / json

![](./assets/3-postman.png)

### Cliente-Service

> - **POST**: http://localhost:8081/api/v1/clientes

> > - Request:

```
{
  "nombre":"Marianela Montalvo",
  "genero":"F",
  "edad":30,
  "identificacion":"123456",
  "direccion":"Amazonas y NNUU",
  "telefono":"097548965",
  "contrasena":"5678",
  "estado":true
}
```

> > - Response:

```
{
    "persona": {
        "id": 6,
        "nombre": "Marianela Montalvo",
        "genero": "F",
        "edad": 30,
        "identificacion": "123456",
        "direccion": "Amazonas y NNUU",
        "telefono": "097548965"
    },
    "personaId": null,
    "estado": true
}
```

> - **GET (All)**: http://localhost:8081/api/v1/clientes

> > - Request:

```

```

> > - Response:

```
[
    {
        "persona": {
            "id": 5,
            "nombre": "Jose Lema",
            "genero": "M",
            "edad": 30,
            "identificacion": null,
            "direccion": null,
            "telefono": null
        },
        "personaId": null,
        "estado": null
    },
    {
        "persona": {
            "id": 6,
            "nombre": "Marianela Montalvo",
            "genero": "F",
            "edad": 30,
            "identificacion": null,
            "direccion": null,
            "telefono": null
        },
        "personaId": null,
        "estado": null
    }
]
```

> - **GET (BY ID)**: http://localhost:8081/api/v1/clientes/6 (id)

> > - Request:

```

```

> > - Response:

```
{
    "persona": {
        "id": 6,
        "nombre": "Marianela Montalvo",
        "genero": "F",
        "edad": 30,
        "identificacion": "123456",
        "direccion": "Amazonas y NNUU",
        "telefono": "097548965"
    },
    "personaId": null,
    "estado": true
}
```

> - **PUT**: http://localhost:8081/api/v1/clientes/6 (id)

> > - Request:

```
{
  "nombre":"Marianela Montalvo",
  "genero":"F",
  "edad":25,
  "identificacion":"785496",
  "direccion":"Amazonas y NNUU",
  "telefono":"097548965",
  "contrasena":"5678",
  "estado":true
}
```

> > - Response:

```
{
    "persona": {
        "id": 6,
        "nombre": "Marianela Montalvo",
        "genero": "F",
        "edad": 25,
        "identificacion": "785496",
        "direccion": "Amazonas y NNUU",
        "telefono": "097548965"
    },
    "personaId": null,
    "estado": true
}
```

> - **DELETE**: http://localhost:8081/api/v1/clientes/5 (id)

> > - Request:

```

```

> > - Response:

```
204 No Content
```

#### SWAGGER

1. Ingresar a la url:

```
    http://localhost:8081/api/v1/swagger-ui.html
```

![](./assets/4-swagger.png)
