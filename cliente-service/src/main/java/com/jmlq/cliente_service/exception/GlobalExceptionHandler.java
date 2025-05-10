package com.jmlq.cliente_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
// import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.stream.Collectors;

// @ControllerAdvice
public class GlobalExceptionHandler {

    // 1) Maneja errores de validación de DTOs (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        String errores = ((BindException) ex)
                .getBindingResult()
                .getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return ResponseEntity
                .badRequest()
                .body(Map.of("errors", errores));
    }

    // 2) Excepciones genéricas (no incluidas arriba)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneric(Exception ex) {
        String raw = ex.getMessage() != null
                ? ex.getMessage()
                : "Ocurrió un error inesperado. Intenta de nuevo más tarde.";

        System.err.println("Excepción genérica capturada: " + raw);
        // Ignorar el caso de SpringDoc NoSuchMethodError
        if (raw.startsWith("Handler dispatch failed: java.lang.NoSuchMethodError")) {
            // Devuelve 200 OK vacío para que Swagger UI continúe
            return ResponseEntity.ok().build();
        }
        // Para cualquier otra excepción, devuelve 500 con mensaje
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("message", raw));
    }
}
