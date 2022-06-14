package com.fdmgroup.ecommercewebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class EcommerceWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceWebappApplication.class, args);
		System.out.println("Starting WebApp...");
	}

}
