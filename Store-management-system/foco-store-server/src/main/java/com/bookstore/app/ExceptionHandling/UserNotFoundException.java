package com.bookstore.app.ExceptionHandling;

public class UserNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(){
    	super();
    }
}
