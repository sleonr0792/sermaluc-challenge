package com.challenge.sermaluc.usuarios.config.exceptions;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerRestController {

    @ExceptionHandler(value = BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> businessException(BusinessException exception) {
        log.error("[Controller][Error] exception Ocurrio un error durante la ejecucion [businessException]: ",
                exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(MessageError.builder()
                        .mensaje(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(value = HttpClientErrorException.Forbidden.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Object> noAccesibleRecurso(Exception exception) {
        log.error("[Controller][Error] exception Ocurrio un error durante la ejecucion  [noAccesibleRecurso]: ",
                exception);
        return new ResponseEntity<>("No se puede acceder a este recurso", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = HttpClientErrorException.NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> recursoNoEncontrado(Exception exception) {
        log.error("[Controller][Error] exception Ocurrio un error durante la ejecucion [recursoNoEncontrado]: ",
                exception);
        return new ResponseEntity<>("Recurso no encontrado", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> genericException(Exception exception) {
        log.error("[Controller][Error] exception Ocurrio un error durante la ejecucion [genericException]: ",
                exception);
        return new ResponseEntity<>("Ha Ocurrido un error Inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Builder
    @Data
    static class MessageError {
        private String mensaje;
    }
}

