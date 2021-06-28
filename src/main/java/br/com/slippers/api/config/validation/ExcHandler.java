package br.com.slippers.api.config.validation;

import java.rmi.AlreadyBoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javassist.NotFoundException;

@RestControllerAdvice
public class ExcHandler {
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ExceptionDTO handle(NotFoundException nfexception) {
        ExceptionDTO exc = new ExceptionDTO();
        exc.setMessage(nfexception.getMessage());
		return exc;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(AlreadyBoundException.class)
	public ExceptionDTO handle(AlreadyBoundException abExc) {
        ExceptionDTO exc = new ExceptionDTO();
        exc.setMessage(abExc.getMessage());
		return exc;
	}
}
