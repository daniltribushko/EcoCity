package com.example.EcoCity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EcoCityApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcoCityApplication.class, args);
	}

}
