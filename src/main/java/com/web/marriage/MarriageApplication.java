package com.web.marriage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "marriage REST API Documentation", description = "Math marriage REST API Documentation", version = "v1", contact = @Contact(name = "Mostafa Mohamed", email = "mostafa19500mahmoud@gmail.com")))
public class MarriageApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(MarriageApplication.class, args);
	}
	// sudo systemctl start mongod
}