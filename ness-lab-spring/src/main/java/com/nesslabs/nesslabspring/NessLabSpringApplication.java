package com.nesslabs.nesslabspring;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
	@OpenAPIDefinition(info = @Info(title = "Timisoara Events Api", version = "3.0", description = ""))
public class NessLabSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(NessLabSpringApplication.class, args);
	}

}
