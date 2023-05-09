package com.nesslabs.nesslabspring;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
public class NessLabSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(NessLabSpringApplication.class, args);
	}

}
