package com.bookstore.app.controllers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bookstore.app.exceptions.UUIDAdditionException;
import com.bookstore.app.exceptions.EmailFormatException;
import com.bookstore.app.exceptions.EmptyBlankFieldsException;
import com.bookstore.app.exceptions.FieldValueNullException;
import com.bookstore.app.exceptions.NullFieldsException;
import com.bookstore.app.exceptions.PhoneNumberFormatException;
import com.bookstore.app.exceptions.ResourceNotFoundException;
import com.bookstore.app.exceptions.TextBooksISBNFormatException;
import com.bookstore.app.exceptions.UUIDUpdateException;
import com.bookstore.app.models.ExceptionResponse;


@ControllerAdvice
public class GlobalExceptionController extends ResponseEntityExceptionHandler {

	  @ExceptionHandler
	  public final ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) { 
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "resource does not exist", "path is " + ((ServletWebRequest)request).getRequest().getRequestURL().toString());
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND); 
	  }
	  
	  @ExceptionHandler
	  public final ResponseEntity<ExceptionResponse> handleISBNFOrmatException(TextBooksISBNFormatException ex, WebRequest request) { 
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "incorrect ISBN format", "path is " + ((ServletWebRequest)request).getRequest().getRequestURL().toString());
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST); 
	  } 
	  
	  @ExceptionHandler
	  public final ResponseEntity<ExceptionResponse> handleUNullFieldsException(NullFieldsException ex, WebRequest request) { 
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "check null fields", "path is " + ((ServletWebRequest)request).getRequest().getRequestURL().toString());
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST); 
	  }
	  
	  @ExceptionHandler
	  public final ResponseEntity<ExceptionResponse> handleEmptyBlankFieldsException(EmptyBlankFieldsException ex, WebRequest request) { 
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "check blank/empty fields", "path is " + ((ServletWebRequest)request).getRequest().getRequestURL().toString());
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST); 
	  }
	  
	  @ExceptionHandler
	  public final ResponseEntity<ExceptionResponse> handleFieldValueNullException(FieldValueNullException ex, WebRequest request) { 
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "field has null string in it", "path is " + ((ServletWebRequest)request).getRequest().getRequestURL().toString());
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST); 
	  }
	  
	  @ExceptionHandler
	  public final ResponseEntity<ExceptionResponse> handlePhoneNumberHasCharException(PhoneNumberFormatException ex, WebRequest request) { 
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "phone number contains character", "path is " + ((ServletWebRequest)request).getRequest().getRequestURL().toString());
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST); 
	  }
	  
	  @ExceptionHandler
	  public final ResponseEntity<ExceptionResponse> handleEmailFormatException(EmailFormatException ex, WebRequest request) { 
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "email format is incorrect", "path is " + ((ServletWebRequest)request).getRequest().getRequestURL().toString());
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST); 
	  }
	  
	  @ExceptionHandler
	  public final ResponseEntity<ExceptionResponse> handleUUIDUpdateException(UUIDUpdateException ex, WebRequest request) { 
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "you can not update ID", "path is " + ((ServletWebRequest)request).getRequest().getRequestURL().toString());
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN); 
	  } 
	  
	  @ExceptionHandler
	  public final ResponseEntity<ExceptionResponse> handleDuplicateResourceException(UUIDAdditionException ex, WebRequest request) { 
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "you can not give UUID", "path is " + ((ServletWebRequest)request).getRequest().getRequestURL().toString());
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN); 
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