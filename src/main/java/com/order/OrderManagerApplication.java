package com.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

	

@SpringBootApplication
public class OrderManagerApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderManagerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OrderManagerApplication.class, args);
		LOGGER.info("*************************************************************");
		LOGGER.info("ORDER MANAGER SYSTEM - LUIS MAGNO NOVAES - rel:1.0 - 12/2022");
		LOGGER.info("*************************************************************");
	}

}
