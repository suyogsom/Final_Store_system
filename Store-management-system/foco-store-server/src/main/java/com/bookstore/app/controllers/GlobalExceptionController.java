package com.bookstore.app.controllers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bookstore.app.exceptions.TextBooksNotFoundException;
import com.bookstore.app.exceptions.UserNotFoundException;
import com.bookstore.app.models.ExceptionResponse;


@ControllerAdvice
public class GlobalExceptionController extends ResponseEntityExceptionHandler {

  @ExceptionHandler
  public final ResponseEntity<ExceptionResponse> handleTextBookNotFoundException(TextBooksNotFoundException ex, WebRequest request) { 
	ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "check your text book ID", "path is " + ((ServletWebRequest)request).getRequest().getRequestURL().toString());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND); 
  }
  
  @ExceptionHandler
  public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) { 
	ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "check your user ID", "path is " + ((ServletWebRequest)request).getRequest().getRequestURL().toString());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND); 
  }
}


/* what is @ControllerAdvice = it is global level exception handling, 
 * all other controller level exceptions can be controlled by @ControllerAdvice
 * 
 * @ExceptionHandler is controller level handler. 
 * Here in @ExceptionHandler()you can specify exception class but will work fine
 * 
 * First write exception for particular controller, then inside @ControllerAdvice,
 * use @ExceptionHandler. Write method to handle that controller's exception. 
 */