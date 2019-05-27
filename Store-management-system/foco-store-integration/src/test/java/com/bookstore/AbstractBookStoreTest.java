package com.bookstore;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = { BookstoreSpringTestConfig.class} )
@ExtendWith(SpringExtension.class)
public abstract class AbstractBookStoreTest {
	
}
