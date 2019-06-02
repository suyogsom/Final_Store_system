package com.bookstore.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication 
public class StoreApiDatabaseApplication  {	
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StoreApiDatabaseApplication.class);
	
	public static void main(String[] args) { 
		SpringApplication.run(StoreApiDatabaseApplication.class, args); 
		System.out.println("\n\nSuccess! Application Started\n\n");
		
		LOGGER.error("Messages logged at ERROR level");
		LOGGER.warn("Messages logged at WARN level");
		LOGGER.info("Messages logged at INFO level");
		LOGGER.debug("Messages logged at DEBUG level");
	}
}
