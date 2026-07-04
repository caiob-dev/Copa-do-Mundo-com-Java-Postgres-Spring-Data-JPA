package br.com.titulos.CopaDoMundo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> handleExceptions(Exception ex) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        ResponseError responseError = new ResponseError(
                ex.getMessage(),
                HttpStatus.NOT_FOUND,
                dtf.format(now)
        );
        return new ResponseEntity(responseError, HttpStatus.NOT_FOUND);
    }
}