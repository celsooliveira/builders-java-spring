package br.com.builders.api.exceptionhandler;

import java.time.OffsetDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.builders.domain.exception.NaoEncontradoException;
import br.com.builders.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NaoEncontradoException.class)
	public ResponseEntity<Object> handleNaoEncontrado(NaoEncontradoException exception, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		ErroException erro = new ErroException();
		erro.setStatus(status.value());
		erro.setDataHora(OffsetDateTime.now());
		erro.setTitulo(exception.getMessage());
		
		return handleExceptionInternal(exception, erro, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException exception, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		ErroException erro = new ErroException();
		erro.setStatus(status.value());
		erro.setDataHora(OffsetDateTime.now());
		erro.setTitulo(exception.getMessage());
		
		return handleExceptionInternal(exception, erro, new HttpHeaders(), status, request);
	}

}
