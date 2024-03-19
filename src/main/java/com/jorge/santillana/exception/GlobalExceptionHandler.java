package com.jorge.santillana.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

	//Validaciones GET
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		String msg = e.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
				.collect(Collectors.joining("\n"));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
	}
	
	//Validaciones POST
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
		String errorMessage = ex.getConstraintViolations().stream().map(violation -> {
			String propertyPath = violation.getPropertyPath().toString();
			String propertyIndex = propertyPath.substring(propertyPath.lastIndexOf('[') + 1,
					propertyPath.lastIndexOf(']'));
			return "Parámetros incorrectos, operación " + propertyIndex + ": " + violation.getMessage();
		}).collect(Collectors.joining("\n"));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	}
}
