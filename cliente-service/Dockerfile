# --- ETAPA 1: Compilación con Maven y Java 17 ---
FROM maven:3.8.5-eclipse-temurin-17 AS builder
WORKDIR /app

# 1) Copiamos pom para cachear dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# 2) Copiamos fuentes y compilamos
COPY src src
RUN mvn clean package -DskipTests -B

# --- ETAPA 2: Imagen ligera de ejecución ---
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# 3) Traemos el .jar empaquetado
COPY --from=builder /app/target/*.jar app.jar

# 4) Exponemos el puerto (configurado vía .env)
EXPOSE ${CLIENTE_SERVICE_SERVER_PORT:-8081}

# 5) Arrancamos la aplicación
ENTRYPOINT ["sh","-c","java -jar /app/app.jar"]
