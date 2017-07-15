package com.hack17.hybo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:meta-inf/spring/app-context.xml")
public class HybodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(HybodbApplication.class, args);
	}
}
