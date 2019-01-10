package com.robotstore.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.robotstore.error.ErrorDetails;

@RestControllerAdvice
@RestController

public class ResponseEntityExceptionHandler {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handle(Exception ex, HttpServletRequest request, HttpServletResponse response) {
	    if (ex instanceof NullPointerException) {
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    } 
	    //particular exceptions
//	    else if(ex instance of ...) {
//		    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getMethod() + "-" + request.getRequestURI());
//		      return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//	    }
	    else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	
	}
}
