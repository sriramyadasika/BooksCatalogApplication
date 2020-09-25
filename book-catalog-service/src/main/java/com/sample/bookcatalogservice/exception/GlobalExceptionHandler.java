package com.sample.bookcatalogservice.exception;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

	@ControllerAdvice
	public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	    @ExceptionHandler(BookCatalogNotFoundException.class)
	    public ResponseEntity<Object>  springHandleNotFound(HttpServletResponse response) throws IOException {
	    	 return new ResponseEntity<Object>(
	   	          "User Id does not exist", new HttpHeaders(), HttpStatus.BAD_REQUEST);
	   	    }
	}


