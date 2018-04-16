package com.example.demo.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class FileException extends RuntimeException {
	
	public FileException() {
        super();
    }
	
	public FileException(String message) {
        super(message);
    }
}
