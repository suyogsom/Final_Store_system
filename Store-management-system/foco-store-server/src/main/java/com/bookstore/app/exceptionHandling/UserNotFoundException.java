package com.bookstore.app.exceptionHandling;

public class UserNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(){
    	super();
    }
}
