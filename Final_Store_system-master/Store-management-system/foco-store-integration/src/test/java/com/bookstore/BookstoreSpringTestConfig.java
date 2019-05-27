package com.bookstore;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;

import com.bookstore.it.BookstoreSpringConfig;

@Configuration
@Import({ BookstoreSpringConfig.class })
@Lazy
public class BookstoreSpringTestConfig {
	
}
