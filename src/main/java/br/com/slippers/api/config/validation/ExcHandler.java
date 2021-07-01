package br.com.slippers.api.config.validation;

import java.rmi.AlreadyBoundException;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import javassist.NotFoundException;

@RestControllerAdvice
public class ExcHandler {
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(AlreadyBoundException.class)
	public ExceptionDTO handle(AlreadyBoundException abExc) {
        ExceptionDTO exc = new ExceptionDTO();
        exc.setMessage(abExc.getMessage());
		return exc;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(WebClientResponseException.class)
	public ExceptionDTO handle(WebClientResponseException abExc) {
        ExceptionDTO exc = new ExceptionDTO();
        exc.setMessage(abExc.getMessage());
		return exc;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(WebClientRequestException.class)
	public ExceptionDTO handle(WebClientRequestException abExc) {
        ExceptionDTO exc = new ExceptionDTO();
        exc.setMessage(abExc.getMessage());
		return exc;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NotFoundException.class)
	public ExceptionDTO handle(NotFoundException abExc) {
        ExceptionDTO exc = new ExceptionDTO();
        exc.setMessage(abExc.getMessage());
		return exc;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ExceptionDTO handle(HttpMessageNotReadableException abExc) {
        ExceptionDTO exc = new ExceptionDTO();
        exc.setMessage(abExc.getMessage());
		return exc;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ExceptionDTO handle(MethodArgumentNotValidException abExc) {
        ExceptionDTO exc = new ExceptionDTO();
        exc.setMessage(abExc.getMessage());
		return exc;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BadCredentialsException.class)
	public ExceptionDTO handle(BadCredentialsException abExc) {
        ExceptionDTO exc = new ExceptionDTO();
        exc.setMessage("Usuário e/ou senha incorretos!");
		return exc;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public ExceptionDTO handle(IllegalArgumentException abExc) {
        ExceptionDTO exc = new ExceptionDTO();
        exc.setMessage(abExc.getMessage());
		return exc;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidDataAccessApiUsageException.class)
	public ExceptionDTO handle(InvalidDataAccessApiUsageException abExc) {
        ExceptionDTO exc = new ExceptionDTO();
        exc.setMessage(abExc.getMessage());
		return exc;
	}
}
