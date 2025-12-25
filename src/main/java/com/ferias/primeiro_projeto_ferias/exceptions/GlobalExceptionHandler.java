package com.ferias.primeiro_projeto_ferias.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.LinkedHashMap;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


@RestControllerAdvice
public class GlobalExceptionHandler {
  
  @ExceptionHandler(RecursoNaoEncontradoExceptions.class)
  public ResponseEntity<Object> handleRecursoNaoEncontradoExceptions(RecursoNaoEncontradoExceptions ex) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", HttpStatus.NOT_FOUND.value());
    body.put("error", "Recurso Não Encontrado");
    body.put("message", ex.getMessage());
    return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
public ResponseEntity<Object> handleGenericExceptions(Exception ex) {  
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", INTERNAL_SERVER_ERROR.value());
    body.put("error", "Erro Interno do Servidor");
    body.put("message", ex.getMessage());
    return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
}

}