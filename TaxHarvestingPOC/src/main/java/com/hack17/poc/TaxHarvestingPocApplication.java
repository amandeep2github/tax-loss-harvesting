package com.hack17.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(value="classpath:spring/app-context.xml")
public class TaxHarvestingPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxHarvestingPocApplication.class, args);
	}
}
