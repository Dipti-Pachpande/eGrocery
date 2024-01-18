package com.assignment.eGrocery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {"classpath:message.properties"})
public class EGroceryApplication {

	public static void main(String[] args) {
		SpringApplication.run(EGroceryApplication.class, args);
	}

}
