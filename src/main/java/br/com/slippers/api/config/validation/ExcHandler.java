package br.com.slippers.api.config.validation;

import java.rmi.AlreadyBoundException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
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
