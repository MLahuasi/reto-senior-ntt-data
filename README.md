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

## EJECUTAR PROYECTOS

#### [Ejecutar cliente-service](./cliente-service/README.md)

#### [Ejecutar cuenta-service](./cuenta-service/README.md)

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
             Cliente (Create)   ------------------>   Cuenta

```

Ejecutar:

**POST**

```
    http://localhost:8081/api/v1/clientes
```

## F1 CRUD

Se adjunta [**EndPoints Postman**](./assets/NTT-DATA.postman_collection.json)

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

## F7: Despliegue de la solución en contenedores

### Levantar Imagen Docker BDD y Rabbit

1. Ingresar al directorio [environment](./environment/)
2. Ejecutar el comando:

```
    docker-compose up --build -d

    NOTA: Una vez que finaliza el proceso de creación se debe esperar unos minutos hasta que se levanten bien los servicios.
```

3. Se crea contenedor `Docker` con subconedores: `2 Postgres`, `1 Rabbit`, `Cliente-Service` y `Cuenta-Service`

   ![](./assets/10-F7-DespliegueContenedores.png)

   **NOTA**: Considerar los puertos que usan los contenedores, se los configura en [docker-compose](./environment/docker-compose.yml).

4. Además se crean las tablas en la BDD

   ![](./assets/2-create-tables.png)

   **NOTA**: Para ingresar a las bdd usar las credenciales registradas en el archivo [docker-compose](./environment/docker-compose.yml).

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
