package com.spring.boot.restfulwebservices;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot REST API Documentation",
				description = "Spring Boot REST API Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Sandip Samag",
						email = "samagsandip@gmail.com",
						url = "https://www.sandipsamag.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.sandipsamag.com/license"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot User Management Documentation",
				url = "https://www.javaguides.net/user_management.html"
		)
)
public class SpringBootRestfulWebservicesApplication {

	@Bean
	public ModelMapper getMpdelMapper() {
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestfulWebservicesApplication.class, args);
	}

}
