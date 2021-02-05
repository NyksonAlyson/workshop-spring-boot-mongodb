package com.nykdev.workshopmongo.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nykdev.workshopmongo.services.exception.ObjectNotFoundException;

// tratamento de excessão quando buscar um usuario não cadastrado 404 error

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StanderError>objectNotFound(ObjectNotFoundException e,HttpServletRequest request){
		HttpStatus status =HttpStatus.NOT_FOUND;
		StanderError err = new StanderError(System.currentTimeMillis(), status.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

}
