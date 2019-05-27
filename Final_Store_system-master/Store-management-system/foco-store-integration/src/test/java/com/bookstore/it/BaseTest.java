package com.bookstore.it;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookstore.AbstractBookStoreTest;
import com.bookstore.internal.RestClient;

public class BaseTest extends AbstractBookStoreTest {

	@Autowired
	protected RestClient restClient;
	
}
