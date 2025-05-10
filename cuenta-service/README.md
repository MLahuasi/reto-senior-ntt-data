# CUENTA-SERVICE

## CAMANDOS MAVEN

> - **Limpiar dependencias:**

```
        mvn dependency:purge-local-repository -DreResolve=false
```

> - **Recompilar:**

```
        mvn clean package
```

> - **Limpiar cache:**

```
        mvn clean
```

> - **Ejecutar**:

```
        mvn clean spring-boot:run
```

## EJECUTAR

1. Ingresar al directorio [cuenta-service](./cuenta-service/) por consola
2. Ejecutar

```
    mvn clean package
    mvn clean spring-boot:run
```

## PRUEBAS

Para las pruebas se uso Postman.
Las solicitudes se las realiza con: Body / raw / json

![](../assets/3-postman.png)

### ENDPOINTS

#### SWAGGER

1. Ingresar a la url:

```
    http://localhost:8082/api/v1/swagger-ui.html
```

![](../assets/5-swagger.png)

[**REGRESAR**](../README.md)
