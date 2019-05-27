package com.bookstore.internal;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

@Component
@Lazy
public class RestClient {

	private RequestSpecification requestSpecification;
	
	@PostConstruct
	public void init()	{
		requestSpecification = new RequestSpecBuilder().setContentType(ContentType.JSON)
													   .setAccept(ContentType.JSON)
													   .setBaseUri("http://localhost:8080")
													   .build();
	}
	
	/**
	 * Get request specification
	 * @return		RequestSpecification
	 */
	public RequestSpecification getRecSpec() {
		return requestSpecification;
	}
}
