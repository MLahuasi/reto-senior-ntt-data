package com.jmlq.cliente_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "Cliente Service API", version = "v1", description = "Gestión de entidades Persona/Cliente"))
@SpringBootApplication(scanBasePackages = "com.jmlq")
public class ClienteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClienteServiceApplication.class, args);
	}

}