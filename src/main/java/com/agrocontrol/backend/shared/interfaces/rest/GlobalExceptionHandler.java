package com.agrocontrol.backend.shared.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class) // Puedes cambiar a Exception.class si quieres manejar todas las excepciones
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException ex, WebRequest request) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now()); // Fecha del error
        errorDetails.put("path", request.getDescription(false).replace("uri=", "")); // Ruta del endpoint
        errorDetails.put("message", ex.getMessage()); // Mensaje de error generado en el servicio

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // Aquí puedes agregar otros métodos @ExceptionHandler para diferentes tipos de excepciones, si es necesario.
}
