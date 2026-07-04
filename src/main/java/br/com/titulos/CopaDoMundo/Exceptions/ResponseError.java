package br.com.titulos.CopaDoMundo.Exceptions;

import org.springframework.http.HttpStatus;

public record ResponseError(String message, HttpStatus httpStatus, String time) {
}
