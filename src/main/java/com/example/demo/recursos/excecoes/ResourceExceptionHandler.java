package com.example.demo.recursos.excecoes;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.servicos.excecoes.DatabaseException;
import com.example.demo.servicos.excecoes.ResourceNotFoundException;

@ControllerAdvice //intercepta as exceções para executar uma possivel resposta
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class) //intercepta qualquer exceção desse tipo
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) { 
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND; //pra retornar o erro 404
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	

	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
		String error = "Database error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}