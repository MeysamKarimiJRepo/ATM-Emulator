package com.meysam.emulator;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class FullSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(FullSecurityApplication.class, args);
	}
}
