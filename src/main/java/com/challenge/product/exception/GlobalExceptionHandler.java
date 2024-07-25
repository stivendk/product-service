package com.challenge.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Controlador de excepciones global para gestionar excepciones en toda la aplicación.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Gestionar ProductNotFoundException.
     * @param ex la excepción lanzada.
     * @return un ResponseEntity con un 404 status code y el mensaje de excepción.
     */
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Gestionar errores genericos.
     * @param ex la excepción lanzada.
     * @return un ResponseEntity con un 500 status code y el mensaje de excepción.
     */
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
