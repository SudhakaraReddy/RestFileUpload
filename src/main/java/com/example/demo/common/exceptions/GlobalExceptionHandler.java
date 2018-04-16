package com.example.demo.common.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(basePackages = {"com.example.demo"})
public class GlobalExceptionHandler {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(FileException.class)
	@ResponseBody
	public ResponseEntity handleException(FileException ex) {
		log.error(ex.getMessage(), ex);
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	//@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseEntity handleException(Exception ex) {
		log.error(ex.getMessage(), ex);
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
