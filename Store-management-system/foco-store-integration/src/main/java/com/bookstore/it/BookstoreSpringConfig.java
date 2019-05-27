package com.bookstore.it;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@ComponentScan(basePackages = { "bookstore.it", "com.bookstore.internal" })
@Lazy
public class BookstoreSpringConfig {
	
}
